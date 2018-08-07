package com.lizhi.ls.parses.intent;

import android.os.Bundle;

import com.lizhi.ls.common.LogzConstant;
import com.lizhi.ls.common.LogzConvert;
import com.lizhi.ls.config.ILogzConfig;
import com.lizhi.ls.parses.IParser;

/**
 * Author : Create by Linxinyuan on 2018/08/07
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public class BundleParse implements IParser<Bundle> {
    @Override
    public Class<Bundle> parseClassType() {
        return Bundle.class;
    }

    @Override
    public String parseString(ILogzConfig configer, Bundle bundle) {
        if (bundle != null) {
            StringBuilder builder = new StringBuilder(bundle.getClass().getName() + " [" +
                    LogzConstant.BR);
            for (String key : bundle.keySet()) {
                builder.append(String.format("'%s' => %s " + LogzConstant.BR, key, LogzConvert.objectToString(configer, bundle.get(key))));
            }
            builder.append("]");
            return builder.toString();
        }
        return null;
    }
}
