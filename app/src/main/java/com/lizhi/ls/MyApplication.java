package com.lizhi.ls;

import android.app.Application;
import android.util.Log;

import com.lizhi.ls.trees.DebugTree;
import com.lizhi.ls.trees.FileSaveTree;

//import com.lizhi.ls.trees.business.LiveTree;
//import com.lizhi.ls.trees.business.RecordTree;
//import com.lizhi.ls.trees.business.VoiceTree;

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
                    .configMimLogLevel(Log.VERBOSE)//config mim output level
                    .configTagPrefix("LizhiFM");//config global tag prefix
            Logz.plant(new DebugTree());
            Logz.plant(new FileSaveTree(this));
//            Logz.plantBTree(new LiveTree());
//            Logz.plantBTree(new VoiceTree());
//            Logz.plantBTree(new RecordTree());
        }
    }

}
