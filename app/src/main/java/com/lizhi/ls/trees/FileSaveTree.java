package com.lizhi.ls.trees;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.lizhi.ls.Logz;
import com.lizhi.ls.MyApplication;
import com.lizhi.ls.base.Tree;
import com.lizhi.ls.channel.FileChannelSink;
import com.lizhi.ls.common.LogzConstant;
import com.lizhi.ls.common.LogzTreeTags;
import com.lizhi.ls.config.ILogzConfig;
import com.lizhi.ls.config.LogzConfiger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okio.Buffer;
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
    private String logLevel;
    private Context mContext;
    private ExecutorService executor;
    //private boolean isGzipLog = true;

    //private static final String FILE_NAME_VERBOSE = "verbose";
    //private static final String FILE_NAME_DEBUG = "debug";
    //private static final String FILE_NAME_INFO = "info";
    //private static final String FILE_NAME_WARN = "warn";
    //private static final String FILE_NAME_ERROR = "error";
    //private static final String FILE_NAME_ASSERT = "assert";
    private static final String FILE_NAME_SUFFIX = "_log.txt";

    //private static final Set<StandardOpenOption> read = EnumSet.of(READ);
    //private static final Set<StandardOpenOption> write = EnumSet.of(WRITE);
    //private static final Set<StandardOpenOption> append = EnumSet.of(WRITE, APPEND);
    private static final String DEFAULT_PATH = Environment.getExternalStorageDirectory().getPath() + "/LizhiFm/Logz/";

    public FileSaveTree(Context context) {
        //get other print msg needed
        mContext = context;
        //executor have only on thread
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    protected ILogzConfig configer() {
        return new LogzConfiger()
                .configShowBorders(false)//config if pretty output
                .configMimLogLevel(Log.WARN);//config mim output level
    }

    @Override
    protected void log(int type, String tag, String message) {
        saveMessageToSDCard(type, tag, message);
        judgeOutputLogLevel(type);
    }

    private void saveMessageToSDCard(int type, final String tag, final String message) {
        // have no sd card in your phone
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Logz.e("sdcard unmounted, skip dump exception");
            return;
        }
        //create file if not exist(Date 2018-08-06)
        String dateFlag = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        File dir = new File(DEFAULT_PATH + dateFlag);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // cut time
        long now = System.currentTimeMillis();
        Date date = new Date(now - now % LogzConstant.LOG_FILE_INTERVAL);
        SimpleDateFormat formater = new SimpleDateFormat("HHmmss");
        mLogFile = new File(DEFAULT_PATH + dateFlag + File.separator + formater.format(date) + "_" + getCurrentProcessName() + FILE_NAME_SUFFIX);
        try {
            if (!mLogFile.exists()) {
                mLogFile.createNewFile();
            }
            // final FileChannelSink sink = new FileChannelSink(FileChannel.open(mLogFile.toPath(), append), Timeout.NONE);//append
            final BufferedSink sink = Okio.buffer(Okio.appendingSink(mLogFile));//append
            // final GzipSink gzipSink = new GzipSink(Okio.buffer(Okio.appendingSink(mLogFile)));//append
            // final BufferedSink sinkZip = Okio.buffer(gzipSink);
            // wirteUserPhoneMessage(sink);//write phone message
            Observable.just(1).
                    observeOn(Schedulers.from(executor))
                    .map(new Function<Integer, Boolean>() {
                        @Override
                        public Boolean apply(Integer integer) {
                            try {
                                /* write file with okio without zip nio*/
                                //writeLogFileWithOkioChannel(tag, message, sink);

                                /* write file with okio without zip */
                                wirteLogFileWithOkio(tag, message, sink);

                                /* write file with okio include zip */
                                //wirteLogFileWithOkioZip(tag, message, gzipSink, sinkZip);

                                //long start = System.currentTimeMillis();
                                //long end = System.currentTimeMillis();
                                //Log.e("time", String.valueOf(end - start));
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
     * Use Okio write one line log to file (NIO)
     *
     * @param tag
     * @param message
     * @param sink
     * @throws Exception
     */
    private void writeLogFileWithOkioChannel(String tag, String message, FileChannelSink sink) throws Exception {
        String timeSecond = new SimpleDateFormat("MM-dd/HH:mm:ss").format(new Date(System.currentTimeMillis()));
        Buffer buffer = new Buffer().writeUtf8(timeSecond + "\t" + getCurrentProcessName() + "\t" + logLevel + tag + ":" + message + LogzConstant.BR);
        sink.write(buffer, buffer.size());
        sink.close();
    }

    /**
     * Use Okio write one line log to file
     *
     * @param tag
     * @param message
     * @param sink
     * @throws Exception
     */
    private void wirteLogFileWithOkio(String tag, String message, BufferedSink sink) throws Exception {
        String timeSecond = new SimpleDateFormat("MM-dd/HH:mm:ss").format(new Date(System.currentTimeMillis()));
        Buffer buffer = new Buffer().writeUtf8(timeSecond + "\t" + getCurrentProcessName() + "\t" + logLevel + tag + ":" + message + LogzConstant.BR);
        sink.write(buffer, buffer.size());
        //close file outputStream resource
        sink.close();
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
    private void wirteLogFileWithOkioZip(String tag, String message,
                                         GzipSink gzipSink, BufferedSink sink) throws Exception {
        String timeSecond = new SimpleDateFormat("MM-dd-HH-mm-ss").format(new Date(System.currentTimeMillis()));
        sink.writeUtf8(timeSecond + "\t" + getCurrentProcessName() + "\t" + logLevel + tag + ":" + message + LogzConstant.BR);
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
    private void wirteUserPhoneMessage(BufferedSink sink) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get current ProcessName
     *
     * @return
     */
    private String getCurrentProcessName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }

    private void judgeOutputLogLevel(int type) {
        //sort with log level
        switch (type) {
            case Log.VERBOSE:
                logLevel = "V/";
                break;
            case Log.INFO:
                logLevel = "I/";
                break;
            case Log.DEBUG:
                logLevel = "D/";
                break;
            case Log.WARN:
                logLevel = "W/";
                break;
            case Log.ERROR:
                logLevel = "E/";
                break;
            case Log.ASSERT:
                logLevel = "A/";
                break;
            default:
                break;
        }
    }
}