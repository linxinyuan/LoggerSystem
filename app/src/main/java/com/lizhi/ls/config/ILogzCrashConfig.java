package com.lizhi.ls.config;

/**
 * Author : Create by Linxinyuan on 2018/08/04
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public interface ILogzCrashConfig extends ILogzGlobalConfig{
    //设置CrashTree日志最小显示级别
    ILogzGlobalConfig configCrashTreeMimLogLevel(int mimLogLevel);
}
