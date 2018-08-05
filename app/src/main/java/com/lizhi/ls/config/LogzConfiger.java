package com.lizhi.ls.config;

import android.util.Log;

import com.lizhi.ls.common.LogzConstant;
import com.lizhi.ls.parses.IParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : logz日志系统配置器
 */
public class LogzConfiger implements ILogzConfig {
    private String globalPrefix;
    private boolean isEnable = true;
    private boolean isShowBorder = false;
    private int mimLogLevel = Log.VERBOSE;
    private List<IParser> mParserList;
    private int mParserLevel = LogzConstant.MAX_CHILD_LEVEL;

    public LogzConfiger() {
        addLogzParserClass(LogzConstant.DEFAULT_PARSE_CLASS);
    }

    @Override
    public ILogzConfig configAllowLog(boolean allowLog) {
        this.isEnable = allowLog;
        return this;
    }

    @Override
    public ILogzConfig configShowBorders(boolean showBorder) {
        this.isShowBorder = showBorder;
        return this;
    }

    @Override
    public ILogzConfig configMimLogLevel(int mimLogLevel) {
        this.mimLogLevel = mimLogLevel;
        return this;
    }

    @Override
    public ILogzConfig configGlobalPrefix(String globalPrefix) {
        this.globalPrefix = globalPrefix;
        return this;
    }

    @Override
    public ILogzConfig configClassParserLevel(int parserLevel) {
        if (parserLevel < 0 || parserLevel > 2)
            mParserLevel = LogzConstant.MAX_CHILD_LEVEL;
        else
            mParserLevel = parserLevel;
        return this;
    }

    @Override
    public ILogzConfig addLogzParserClass(Class<? extends IParser>... parsers){
        mParserList = new ArrayList<IParser>();//list init
        for (Class<? extends IParser> cla : parsers) {
            try {
                mParserList.add(0, cla.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }


    public boolean isEnable() {
        return this.isEnable;
    }

    public int getMimLogLevel() {
        return this.mimLogLevel;
    }

    public String getGlobalPrefix() {
        return this.globalPrefix;
    }

    public boolean isShowBorder() {
        return isShowBorder;
    }

    public List<IParser> getParserList() {
        return mParserList;
    }

    public int getParserLevel() {
        return mParserLevel;
    }
}