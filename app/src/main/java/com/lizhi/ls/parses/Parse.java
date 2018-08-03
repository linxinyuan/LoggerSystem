package com.lizhi.ls.parses;

import com.lizhi.ls.common.LogzConstant;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public interface Parse<T> {
    String LINE_SEPARATOR = LogzConstant.BR;

    String parseString(T t);
}
