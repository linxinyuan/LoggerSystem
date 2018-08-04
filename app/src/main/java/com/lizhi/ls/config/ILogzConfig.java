package com.lizhi.ls.config;

import com.lizhi.ls.parses.IParser;

import java.util.List;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : logz系统配置接口类
 */
public interface ILogzConfig {
    //设置是否输出日志
    ILogzConfig configAllowLog(boolean allowLog);

    //设置是否显示排版线条
    ILogzConfig configShowBorders(boolean showBorder);

    //设置日志最小显示级别
    ILogzConfig configMimLogLevel(int mimLogLevel);

    //设置日志tag前缀
    ILogzConfig configGlobalPrefix(String globalPrefix);

    //设置解析类(父类与类成员)层级(考虑到反射效率,取值范围限定是0-2,默认为1)
    ILogzConfig configClassParserLevel(int parserLevel);

    //添加自定义解析器
    ILogzConfig addLogzParserClass(Class<? extends IParser>... classes);

    //获取是否输出日志标志位
    boolean isEnable();

    //获取日志最小输出级别
    int getMimLogLevel();

    //获取特定标签头设置
    String getGlobalPrefix();

    //获取是否输出格式化日志标志位
    boolean isShowBorder();

    //获取自定义转换器列表
    List<IParser> getParserList();

    //获取日志解析类的上下最大层级
    int getParserLevel();
}
