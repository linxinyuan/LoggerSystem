package com.lizhi.ls.trees;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.lizhi.ls.base.Tree;
import com.lizhi.ls.common.LogzConstant;
import com.lizhi.ls.config.ILogzConfig;
import com.lizhi.ls.config.LogzConfiger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : 输出到File的日志树节点(默认日志输出级别为Debug)
 */
public class FileSaveTree extends Tree {
    private File mLogFile;
    private Context mContext;
    private ExecutorService executor;

    private static final String FILE_NAME_VERBOSE = "verbose";
    private static final String FILE_NAME_DEBUG = "debug";
    private static final String FILE_NAME_INFO = "info";
    private static final String FILE_NAME_WARN = "warn";
    private static final String FILE_NAME_ERROR = "error";
    private static final String FILE_NAME_ASSERT = "assert";
    private static final String FILE_NAME_SUFFIX = ".log";

    private static final String DEFAULT_PATH = Environment.getExternalStorageDirectory().getPath() + "/LizhiFm/Logz/";

    public FileSaveTree() {
        //executor have only on thread
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    protected ILogzConfig configer() {
        return new LogzConfiger()
                .configAllowLog(true)//config log can output
                .configShowBorders(false)//config if pretty output
                .configClassParserLevel(1)//config class paser level
                .configMimLogLevel(Log.DEBUG)//config mim output level
                .configGlobalPrefix("LizhiFM_File");//config tag prefix
    }

    @Override
    protected void log(int type, String tag, String message) {
        saveMessageToSDCard(type, tag, message);
    }

    private void saveMessageToSDCard(int type, final String tag, final String message) {
        // have no sd card in your phone
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            System.out.print("sdcard unmounted, skip dump exception");
            return;
        }
        //create file if not exist
        String dateFlag = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        File dir = new File(DEFAULT_PATH + dateFlag);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //sort with log level
        String fileName = FILE_NAME_VERBOSE;
        switch (type) {
            case Log.VERBOSE:
                fileName = FILE_NAME_VERBOSE;
                break;
            case Log.INFO:
                fileName = FILE_NAME_INFO;
                break;
            case Log.DEBUG:
                fileName = FILE_NAME_DEBUG;
                break;
            case Log.WARN:
                fileName = FILE_NAME_WARN;
                break;
            case Log.ERROR:
                fileName = FILE_NAME_ERROR;
                break;
            case Log.ASSERT:
                fileName = FILE_NAME_ASSERT;
                break;
            default:
                break;
        }
        mLogFile = new File(DEFAULT_PATH + dateFlag + File.separator + fileName + FILE_NAME_SUFFIX);
        try {
            if (!mLogFile.exists()) {
                mLogFile.createNewFile();
            }
            final GzipSink gzipSink = new GzipSink(Okio.buffer(Okio.sink(mLogFile)));
            final BufferedSink sink = Okio.buffer(gzipSink);
            wirteUserPhoneMessage(sink);//write phone message
            Observable.just(1).
                    observeOn(Schedulers.from(executor))
                    .map(new Function<Integer, Boolean>() {
                        @Override
                        public Boolean apply(Integer integer) {
                            try {
                                //write file with okio include zip
                                wirteLogFileWithOkio(tag, message, gzipSink, sink);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return true;
                        }
                    }).subscribe();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Use Okio write one line log to file
     *
     * @param tag
     * @param message
     * @param gzipSink
     * @param sink
     * @throws Exception
     */
    public void wirteLogFileWithOkio(String tag, String message,
                                         GzipSink gzipSink, BufferedSink sink) throws Exception {
        String timeSecond = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date(System.currentTimeMillis()));
        sink.writeUtf8(timeSecond + "/t" + tag + "/t" + message + LogzConstant.BR);
        //close file outputStream resource
        sink.close();
        gzipSink.close();
    }

    /**
     * Print user phone message in log file header
     *
     * @param sink
     * @throws Exception
     */
    public void wirteUserPhoneMessage(BufferedSink sink) {
        if (null == mContext)
            return;
        try {
            //application version name and version code
            PackageManager packageManager = mContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            sink.writeUtf8("App Version:");
            sink.writeUtf8(packageInfo.versionName);
            sink.writeUtf8(String.valueOf('_'));
            sink.writeUtf8(String.valueOf(packageInfo.versionCode) + LogzConstant.BR);

            //phone android version
            sink.writeUtf8("OS Version:");
            sink.writeUtf8(Build.VERSION.RELEASE);
            sink.writeUtf8(String.valueOf('_'));
            sink.writeUtf8(String.valueOf(Build.VERSION.SDK_INT) + LogzConstant.BR);

            //phone builder
            sink.writeUtf8("Vendor:");
            sink.writeUtf8(Build.MANUFACTURER);

            //phone type
            sink.writeUtf8("Model:");
            sink.writeUtf8(Build.MODEL);

            //cpu type
            sink.writeUtf8("CPU ABI:");
            sink.writeUtf8(Build.CPU_ABI);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}