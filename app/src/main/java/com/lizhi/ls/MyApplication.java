package com.lizhi.ls;

import android.app.Application;
import com.lizhi.ls.trees.DebugTree;

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

        }
    }
}
