package com.lizhi.ls.common;

import com.lizhi.ls.config.LogzConfigCenter;
import com.lizhi.ls.parses.IParser;
import com.lizhi.ls.parses.CollectionParse;
import com.lizhi.ls.parses.IntentParse;
import com.lizhi.ls.parses.MapParse;

import java.util.List;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : Logz日志常量类
 */
public class LogzConstant {
    // 分割线方位
    public static final int DIVIDER_TOP = 1;
    public static final int DIVIDER_BOTTOM = 2;
    public static final int DIVIDER_CENTER = 4;
    public static final int DIVIDER_NORMAL = 3;

    public static final int LINE_MAX = 1024 * 3;// 最大日志长度
    public static final int CALL_STACK_INDEX = 5;// 堆栈寻址下标
    public static final int JSON_PRINT_INDENT = 4;// json输出缩进

    public static final String TIP_OBJECT_NULL = "Object[object is null]";//空类
    public static final String BR = System.getProperty("line.separator");// 换行符

    public static final Class<? extends IParser>[] DEFAULT_PARSE_CLASS = new Class[]{
            IntentParse.class, CollectionParse.class, MapParse.class
    };

    public static List<IParser> getParserList() {
        return LogzConfigCenter.getInstance().getParserList();
    }
}
