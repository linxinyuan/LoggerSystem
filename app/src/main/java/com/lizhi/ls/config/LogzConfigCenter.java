package com.lizhi.ls.config;

import android.util.Log;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : log日志系统全局配置中心
 */
public class LogzConfigCenter implements ILogzGlobalConfig {
    private String globalPrefix;
    private boolean isEnable = true;
    private boolean isShowBorder = false;
    private int mimLogLevel = Log.VERBOSE;

    private LogzConfigCenter() {
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
}
