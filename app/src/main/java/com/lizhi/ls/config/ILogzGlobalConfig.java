package com.lizhi.ls.config;

import com.lizhi.ls.parses.IParser;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : logz系统全局配置接口类
 */
public interface ILogzGlobalConfig {
    //设置是否输出日志
    ILogzGlobalConfig configAllowLog(boolean allowLog);

    //设置是否显示排版线条
    ILogzGlobalConfig configShowBorders(boolean showBorder);

    //设置日志最小显示级别
    ILogzGlobalConfig configMimLogLevel(int mimLogLevel);

    //设置全局日志tag前缀
    ILogzGlobalConfig configGlobalPrefix(String globalPrefix);

    //设置解析类(父类与类成员)层级(考虑到反射效率,取值范围限定是0-2,默认为1)
    ILogzGlobalConfig configClassParserLevel(int parserLevel);

    //添加自定义解析器
    ILogzGlobalConfig addLogzParserClass(Class<? extends IParser>... classes);
}
