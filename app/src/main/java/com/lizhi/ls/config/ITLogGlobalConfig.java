package com.lizhi.ls.config;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : log系统全局配置接口类
 */
public interface ITLogGlobalConfig {
    //设置是否输出日志
    ITLogGlobalConfig configAllowLog(boolean allowLog);

    //设置是否显示排版线条
    ITLogGlobalConfig configShowBorders(boolean showBorder);

    //设置日志最小显示级别
    ITLogGlobalConfig configMimLogLevel(int mimLogLevel);

    //设置全局日志tag前缀
    ITLogGlobalConfig configGlobalPrefix(String globalPrefix);
}
