package com.lizhi.ls.common;

import android.os.Environment;

import com.lizhi.ls.config.LogzConfigCenter;
import com.lizhi.ls.parses.ArrayParser;
import com.lizhi.ls.parses.IParser;
import com.lizhi.ls.parses.CollectionParser;
import com.lizhi.ls.parses.MapParser;

import java.io.File;
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
    public static final int JSON_PRINT_INDENT = 4;// Json输出缩进
    public static final int MAX_CHILD_LEVEL = 1;//Object最大解析层级(父子)

    public static final String TIP_OBJECT_NULL = "Object[object is null]";//空类
    public static final String BR = System.getProperty("line.separator");// 换行符

    public static final Class<? extends IParser>[] DEFAULT_PARSE_CLASS = new Class[]{
            CollectionParser.class, MapParser.class
    };

    public static List<IParser> getParserList() {
        return LogzConfigCenter.getInstance().getParserList();
    }
}
