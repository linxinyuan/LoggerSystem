package com.lizhi.ls.common;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : Logz转化类
 */
public class LogzConvert {
    /**
     * 日志框分割线打印
     *
     * @param dividerTop
     * @return
     */
    public static String printDividingLine(int dividerTop) {
        switch (dividerTop) {
            case LogzConstant.DIVIDER_TOP:
                return "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case LogzConstant.DIVIDER_BOTTOM:
                return "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case LogzConstant.DIVIDER_NORMAL:
                return "║ ";
            case LogzConstant.DIVIDER_CENTER:
                return "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
            default:
                break;
        }
        return "";
    }
}
