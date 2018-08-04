package com.lizhi.ls;

import android.app.Application;
import android.util.Log;

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
            Logz.getLogConfigCenter()
                    .configAllowLog(true)//config log can output
                    .configShowBorders(false)//config if pretty output
                    .configClassParserLevel(1)//config class paser level
                    .configMimLogLevel(Log.VERBOSE)//config mim output level
                    .configGlobalPrefix("LizhiFM");//config global tag prefix

            //TODO If need application context?
            Logz.plant(new DebugTree());
            Logz.plant(new FileSaveTree());
        }
    }
}
