package com.lizhi.ls.parses;

import java.util.Map;

/**
 * Author : Create by Linxinyuan on 2018/08/03
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public class MapParse implements IParser {
    @Override
    public Class parseClassType() {
        return Map.class;
    }

    @Override
    public String parseString(Object o) {
        return null;
    }
}
