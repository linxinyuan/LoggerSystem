package com.lizhi.ls;

import android.app.Application;

import com.lizhi.ls.trees.DebugTree;
import com.lizhi.ls.trees.FileSaveTree;
import com.lizhi.ls.trees.business.LiveTree;

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
//            Logz.getLogConfigCenter()
//                    .configAllowLog(true)//config log can output
//                    .configShowBorders(true)//config if pretty output
//                    .configClassParserLevel(1)//config class paser level
//                    .configMimLogLevel(Log.VERBOSE)//config mim output level
//                    .configTagPrefix("LizhiFM");//config global tag prefix

            Logz.plant(new LiveTree());
            Logz.plant(new DebugTree());
            //TODO If need application context?
            Logz.plant(new FileSaveTree(this));
        }
    }
}
