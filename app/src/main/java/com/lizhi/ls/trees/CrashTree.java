package com.lizhi.ls.trees;

import android.util.Log;

import com.lizhi.ls.Logz;
import com.lizhi.ls.base.Tree;
import com.lizhi.ls.common.LogzTreeTags;
import com.lizhi.ls.config.ILogzConfig;
import com.lizhi.ls.config.LogzConfiger;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : 输出Crash的日志树节点(默认日志输出级别为Warn)
 */
public class CrashTree extends Tree {
    @Override
    protected ILogzConfig configer() {
        return new LogzConfiger()
                .configShowBorders(true)//config if pretty output
                .configMimLogLevel(Log.ERROR);//config mim output level
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
