package com.lizhi.ls.config;

import android.util.Log;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : log日志系统全局配置中心
 */
public class TLogConfigCenter implements ITLogGlobalConfig {
    private String globalPrefix;
    private boolean isEnable = true;
    private boolean isShowBorder = false;
    private int mimLogLevel = Log.VERBOSE;

    private TLogConfigCenter() {
    }

    public static TLogConfigCenter getInstance() {
        return TLogConfigCenterInstance.INSTANCE;
    }

    public static class TLogConfigCenterInstance {
        private static final TLogConfigCenter INSTANCE = new TLogConfigCenter();
    }

    @Override
    public ITLogGlobalConfig configAllowLog(boolean allowLog) {
        this.isEnable = allowLog;
        return this;
    }

    @Override
    public ITLogGlobalConfig configShowBorders(boolean showBorder) {
        this.isShowBorder = showBorder;
        return this;
    }

    @Override
    public ITLogGlobalConfig configMimLogLevel(int mimLogLevel) {
        this.mimLogLevel = mimLogLevel;
        return this;
    }

    @Override
    public ITLogGlobalConfig configGlobalPrefix(String globalPrefix) {
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
