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
                    .configAllowLog(true)
                    .configShowBorders(true)
                    .configClassParserLevel(1);
            Logz.plant(new DebugTree());
            Logz.plant(new FileSaveTree(getApplicationContext(), Log.DEBUG));
        }
    }
}
