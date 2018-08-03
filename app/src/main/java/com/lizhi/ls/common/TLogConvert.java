package com.lizhi.ls.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Create by Linxinyuan on 2018/08/02
 * Email : linxinyuan@lizhi.fm
 * Desc : android dev
 */
public class TLogConvert {
    /**
     * 日志框分割线打印
     *
     * @param dividerTop
     * @return
     */
    public static String printDividingLine(int dividerTop) {
        switch (dividerTop) {
            case TLogConstant.DIVIDER_TOP:
                return "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case TLogConstant.DIVIDER_BOTTOM:
                return "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case TLogConstant.DIVIDER_NORMAL:
                return "║ ";
            case TLogConstant.DIVIDER_CENTER:
                return "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
            default:
                break;
        }
        return "";
    }
}
