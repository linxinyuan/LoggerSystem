package com.lizhi.ls.trees;

import android.util.Log;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public interface ITree {
    //Log.VERBOSE
    void v(String message, Object... args);
    void v(Throwable t, String message, Object... args);
    void v(Throwable t);

    //Log.DEBUG
    void d(String message, Object... args);
    void d(Throwable t, String message, Object... args);
    void d(Throwable t);

    //Log.INFO
    void i(String message, Object... args);
    void i(Throwable t, String message, Object... args);
    void i(Throwable t);

    //Log.WRAN
    void w(String message, Object... args);
    void w(Throwable t, String message, Object... args);
    void w(Throwable t);

    //Log.ERROR
    void e(String message, Object... args);
    void e(Throwable t, String message, Object... args);
    void e(Throwable t);

    //Log.ASSERT
    void wtf(String message, Object... args);
    void wtf(Throwable t, String message, Object... args);
    void wtf(Throwable t);

    //Log.CUSTOM
    void log(int priority, String message, Object... args);
    void log(int priority, Throwable t, String message, Object... args);
    void log(int priority, Throwable t);

    //Log.SPECIAL
    void json(String j);
    void xml(String x);
}
