package com.lizhi.ls.parses;

import android.content.Intent;

/**
 * Author : Create by Linxinyuan on 2018/08/03
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public class IntentParse implements IParser {
    @Override
    public Class parseClassType() {
        return Intent.class;
    }

    @Override
    public String parseString(Object o) {
        return null;
    }
}
