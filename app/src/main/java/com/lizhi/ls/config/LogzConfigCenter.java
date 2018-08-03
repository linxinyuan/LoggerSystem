package com.lizhi.ls.config;

import android.util.Log;

import com.lizhi.ls.common.LogzConstant;
import com.lizhi.ls.parses.IParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : logz日志系统全局配置中心
 */
public class LogzConfigCenter implements ILogzGlobalConfig {
    private String globalPrefix;
    private boolean isEnable = true;
    private boolean isShowBorder = false;
    private int mimLogLevel = Log.VERBOSE;
    private List<IParser> mParserList;
    private int mParserLevel = LogzConstant.MAX_CHILD_LEVEL;

    private LogzConfigCenter() {
        addLogzParserClass(LogzConstant.DEFAULT_PARSE_CLASS);
    }

    public static LogzConfigCenter getInstance() {
        return TLogConfigCenterInstance.INSTANCE;
    }

    public static class TLogConfigCenterInstance {
        private static final LogzConfigCenter INSTANCE = new LogzConfigCenter();
    }


    @Override
    public ILogzGlobalConfig configAllowLog(boolean allowLog) {
        this.isEnable = allowLog;
        return this;
    }

    @Override
    public ILogzGlobalConfig configShowBorders(boolean showBorder) {
        this.isShowBorder = showBorder;
        return this;
    }

    @Override
    public ILogzGlobalConfig configMimLogLevel(int mimLogLevel) {
        this.mimLogLevel = mimLogLevel;
        return this;
    }

    @Override
    public ILogzGlobalConfig configGlobalPrefix(String globalPrefix) {
        this.globalPrefix = globalPrefix;
        return this;
    }

    @Override
    public ILogzGlobalConfig configClassParserLevel(int parserLevel) {
        if (parserLevel < 0 || parserLevel > 2)
            mParserLevel = LogzConstant.MAX_CHILD_LEVEL;
        else
            mParserLevel = parserLevel;
        return this;
    }

    @Override
    public ILogzGlobalConfig addLogzParserClass(Class<? extends IParser>... parsers){
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

    public int getLogLevel() {
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
