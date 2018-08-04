package com.lizhi.ls.trees;

import android.util.Log;

import com.lizhi.ls.base.Tree;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : 输出到Logcat的日志树节点(默认日志输出级别为Debug)
 */
public class DebugTree extends Tree {
    public DebugTree() {
        this(Log.DEBUG);
    }

    public DebugTree(int logLevel) {
        level(logLevel);
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