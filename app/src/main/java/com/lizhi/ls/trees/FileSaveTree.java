package com.lizhi.ls.trees;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.lizhi.ls.base.Tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : 输出到File的日志树节点(默认日志输出级别为Debug)
 */
public class FileSaveTree extends Tree {
    private File mLogFile;
    private Context mContext;
    private String mDirectory;

    private static final String FILE_NAME_VERBOSE = "verbose";
    private static final String FILE_NAME_DEBUG = "debug";
    private static final String FILE_NAME_INFO = "info";
    private static final String FILE_NAME_WARN = "warn";
    private static final String FILE_NAME_ERROR = "error";
    private static final String FILE_NAME_ASSERT = "assert";
    private static final String FILE_NAME_SUFFIX = ".log";

    private static final String DEFAULT_PATH = Environment.getExternalStorageDirectory().getPath() + "/LizhiFm/Logz/";

    public FileSaveTree(Context context, int logLevel){
        level(logLevel);
        this.mContext = context;
        this.mDirectory = DEFAULT_PATH;
    }

    @Override
    protected void log(int type, String tag, String message) {
        saveMessageToSDCard(type, tag, message);
    }

    private void saveMessageToSDCard(int type, String tag, String message){
        // have no sd card in your phone
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            System.out.print("sdcard unmounted, skip dump exception");
            return;
        }
        //create file if not exist
        String dateFlag = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        String timeSecond = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date(System.currentTimeMillis()));
        File dir = new File(mDirectory + dateFlag);
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
        mLogFile = new File(mDirectory + dateFlag + File.separator + fileName + FILE_NAME_SUFFIX);
        try {
            if (!mLogFile.exists()) {
                mLogFile.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(mLogFile, true)));
            printWriter.println(timeSecond);
            printWriter.println(tag + "\t" + message);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}