package com.lizhi.ls.common;

import com.lizhi.ls.Logz;
import com.lizhi.ls.parses.IParser;

import org.xml.sax.Parser;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

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

    /**
     * 将对象转化为String
     *
     * @param object
     * @return
     */
    public static String objectToString(Object object) {
        if (object == null) {
            return LogzConstant.TIP_OBJECT_NULL;
        }
        // change if is array
        if (object.getClass().isArray()) {
            return parseArray(object);
        }
        // change by parser
        if (LogzConstant.getParserList() != null && LogzConstant.getParserList().size() > 0) {
            for (IParser parser : LogzConstant.getParserList()) {
                if (parser.parseClassType().isAssignableFrom(object.getClass())) {
                    return parser.parseString(object);
                }
            }
        }
        //default change
        return object.toString();
    }

    /**
     * 将数组内容转化为字符串
     *
     * @param array
     * @return
     */
    private static String parseArray(Object array) {
        StringBuilder result = new StringBuilder();
        traverseArray(result, array);
        return result.toString();
    }

    /**
     * 遍历数组
     *
     * @param result
     * @param array
     */
    private static void traverseArray(StringBuilder result, Object array) {
        if (getArrayDimension(array) == 1) {
            switch (getArrayType(array)) {
                case 'I':
                    result.append(Arrays.toString((int[]) array));
                    break;
                case 'D':
                    result.append(Arrays.toString((double[]) array));
                    break;
                case 'Z':
                    result.append(Arrays.toString((boolean[]) array));
                    break;
                case 'B':
                    result.append(Arrays.toString((byte[]) array));
                    break;
                case 'S':
                    result.append(Arrays.toString((short[]) array));
                    break;
                case 'J':
                    result.append(Arrays.toString((long[]) array));
                    break;
                case 'F':
                    result.append(Arrays.toString((float[]) array));
                    break;
                case 'L':
                    Object[] objects = (Object[]) array;
                    result.append("[");
                    for (int i = 0; i < objects.length; ++i) {
                        result.append(objectToString(objects[i]));
                        if (i != objects.length - 1) {
                            result.append(",");
                        }
                    }
                    result.append("]");
                    break;
                default:
                    result.append(Arrays.toString((Object[]) array));
                    break;
            }
        } else {
            result.append("[");
            for (int i = 0; i < ((Object[]) array).length; i++) {
                traverseArray(result, ((Object[]) array)[i]);
                if (i != ((Object[]) array).length - 1) {
                    result.append(",");
                }
            }
            result.append("]");
        }
    }

    /**
     * 获取数组的纬度
     *
     * @param object
     * @return
     */
    private static int getArrayDimension(Object object) {
        int dim = 0;
        for (int i = 0; i < object.toString().length(); ++i) {
            if (object.toString().charAt(i) == '[') {
                ++dim;
            } else {
                break;
            }
        }
        return dim;
    }

    /**
     * 获取数组类型
     *
     * @param object 如L为int型
     * @return
     */
    private static char getArrayType(Object object) {
        if (object.getClass().isArray()) {
            String str = object.toString();
            return str.substring(str.lastIndexOf("[") + 1, str.lastIndexOf("[") + 2).charAt(0);
        }
        return 0;
    }
}
