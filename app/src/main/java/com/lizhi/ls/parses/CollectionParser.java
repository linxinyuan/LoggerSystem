package com.lizhi.ls.parses;

import java.util.Collection;

/**
 * Author : Create by Linxinyuan on 2018/08/03
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public class CollectionParser implements IParser {
    @Override
    public Class parseClassType() {
        return Collection.class;
    }

    @Override
    public String parseString(Object o) {
        return null;
    }
}
