package com.lizhi.ls.config;

/**
 * Author : Create by Linxinyuan on 2018/08/04
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public interface ILogzFileConfig extends ILogzGlobalConfig {
    //设置FileSave日志最小显示级别
    ILogzGlobalConfig configFileSaveMimLogLevel(int mimLogLevel);

    //设置FileSave是否显示排版线条
    ILogzGlobalConfig configFileSaveShowBorders(boolean showBorder);
}
