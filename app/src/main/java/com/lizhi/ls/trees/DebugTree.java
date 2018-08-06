package com.lizhi.ls.trees;

import android.util.Log;

import com.lizhi.ls.base.Tree;
import com.lizhi.ls.common.LogzTreeTags;
import com.lizhi.ls.config.ILogzConfig;
import com.lizhi.ls.config.LogzConfiger;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : 输出到Logcat的日志树节点(默认日志输出级别为Debug)
 */
public class DebugTree extends Tree {
    @Override
    protected ILogzConfig configer() {
        return new LogzConfiger()
                .configShowBorders(true)//config if pretty output
                .configMimLogLevel(Log.DEBUG);//config mim output level
    }

    @Override
    protected void log(int type, String tag, String message) {
        switch (type) {
            case Log.VERBOSE:
                Log.v(tag, message);
                break;
            case Log.INFO:
                Log.i(tag, message);
                break;
            case Log.DEBUG:
                Log.d(tag, message);
                break;
            case Log.WARN:
                Log.w(tag, message);
                break;
            case Log.ERROR:
                Log.e(tag, message);
                break;
            case Log.ASSERT:
                Log.wtf(tag, message);
                break;
            default:
                break;
        }
    }
}