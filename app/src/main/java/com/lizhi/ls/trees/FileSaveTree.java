package com.lizhi.ls.trees;

import android.content.Context;
import android.util.Log;

import com.lizhi.ls.base.Tree;
import com.lizhi.ls.config.ILogzConfig;
import com.lizhi.ls.config.LogzConfiger;
import com.lizhi.ls.manager.LogSaveManager;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : 输出到File的日志树节点(默认日志输出级别为Debug)
 */
public class FileSaveTree extends Tree {
    private LogSaveManager lsMannager;

    public FileSaveTree(Context context) {
        //log save file manager
        lsMannager = new LogSaveManager(context);
    }

    @Override
    protected ILogzConfig configer() {
        return new LogzConfiger()
                .configShowBorders(false)//配置输出格式化为否
                .configMimLogLevel(Log.WARN);//配置输出到文件的最小级别Warn
    }

    @Override
    protected void log(int type, String tag, String message) {
        lsMannager.saveMessageToSDCard(type, tag, message);
    }
}