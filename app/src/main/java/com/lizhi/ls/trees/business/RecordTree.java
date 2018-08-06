package com.lizhi.ls.trees.business;

import com.lizhi.ls.common.LogzTreeTags;
import com.lizhi.ls.config.ILogzConfig;
import com.lizhi.ls.config.LogzConfiger;
import com.lizhi.ls.trees.business.base.ProductTree;

/**
 * Author : Create by Linxinyuan on 2018/08/06
 * Email : linxinyuan@lizhi.fm
 * Desc : Bussiness record tree sample
 */
public class RecordTree extends ProductTree {
    @Override
    protected ILogzConfig configer() {
        return new LogzConfiger()
                .configTagPrefix(LogzTreeTags.TAG_RECORD);
    }

    @Override
    protected void flatLog(int type, String tag, String message) {

    }
}
