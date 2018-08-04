package com.lizhi.ls.config;

/**
 * Author : Create by Linxinyuan on 2018/08/04
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public interface ILogzDebugConfig extends ILogzGlobalConfig {
    //设置DebugTree日志最小显示级别
    ILogzGlobalConfig configDebugTreeMimLogLevel(int mimLogLevel);
}
