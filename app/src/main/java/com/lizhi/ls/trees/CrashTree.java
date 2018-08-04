package com.lizhi.ls.trees;

import android.util.Log;

import com.lizhi.ls.base.Tree;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : 输出Crash的日志树节点(默认日志输出级别为Warn)
 */
public class CrashTree extends Tree {
    public CrashTree(){
        this(Log.WARN);
    }

    public CrashTree(int logLevel){
        level(logLevel);
    }

    @Override
    protected void log(int type, String tag, String message) {
        // TODO add log entry to circular buffer.
    }

    public static void logWarning(Throwable t) {
        // TODO report non-fatal warning.
    }

    public static void logError(Throwable t) {
        // TODO report non-fatal error.
    }
}
