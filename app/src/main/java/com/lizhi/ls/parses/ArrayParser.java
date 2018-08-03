package com.lizhi.ls.parses;

import java.util.Arrays;

/**
 * Author : Create by Linxinyuan on 2018/08/03
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public class ArrayParser implements IParser {
    @Override
    public Class parseClassType() {
        return Arrays.class;
    }

    @Override
    public String parseString(Object o) {
        return null;
    }
}
