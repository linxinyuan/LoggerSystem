package com.lizhi.ls.trees.business;

import com.lizhi.ls.config.ILogzConfig;
import com.lizhi.ls.trees.DebugTree;

/**
 * Author : Create by Linxinyuan on 2018/08/06
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public abstract class ProductTree extends DebugTree {
    @Override
    protected ILogzConfig configer() {
        return super.configer();
    }

    @Override
    protected void log(int type, String tag, String message) {
        super.log(type, tag, message);
        flatLog(type, tag, message);
    }

    protected abstract void flatLog(int type, String tag, String message);
}
