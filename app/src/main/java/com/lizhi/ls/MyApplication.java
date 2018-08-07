package com.lizhi.ls;

import android.app.Application;
import android.util.Log;

import com.lizhi.ls.trees.CrashTree;
import com.lizhi.ls.trees.DebugTree;
import com.lizhi.ls.trees.FileSaveTree;

/**
 * Author : Create by Linxinyuan on 2018/08/01
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Logz.getLogGlobalConfigCenter()
                    .configAllowLog(true)//config log can output
                    .configShowBorders(true)//config if pretty output
                    .configClassParserLevel(1)//config class paser level
                    .configMimLogLevel(Log.VERBOSE);//config mim output level
            // Plant tree
            Logz.plant(new DebugTree());
            Logz.plant(new CrashTree());
            Logz.plant(new FileSaveTree(this));
        }
    }

}
