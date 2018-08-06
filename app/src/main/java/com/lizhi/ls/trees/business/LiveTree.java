package com.lizhi.ls.trees.business;

import com.lizhi.ls.common.LogzTreeTags;
import com.lizhi.ls.config.ILogzConfig;
import com.lizhi.ls.config.LogzConfiger;

/**
 * Author : Create by Linxinyuan on 2018/08/06
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public class LiveTree extends ProductTree {
    @Override
    protected ILogzConfig configer() {
        return new LogzConfiger()
                .configTagPrefix(LogzTreeTags.TAG_LIVE);
    }

    @Override
    protected void flatLog(int type, String tag, String message) {
        //To due with log with your bussiness
    }
}
