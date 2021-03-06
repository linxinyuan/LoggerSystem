package com.lizhi.ls.parses;

import com.lizhi.ls.common.LogzConstant;
import com.lizhi.ls.common.LogzConvert;

import java.util.Collection;
import java.util.Iterator;

/**
 * Author : Create by Linxinyuan on 2018/08/03
 * Email : linxinyuan@lizhi.fm
 * Desc : Collection接口对象解析器
 */
public class CollectionParser implements IParser<Collection> {
    @Override
    public Class parseClassType() {
        return Collection.class;
    }

    @Override
    public String parseString(Collection collection) {
        String simpleName = collection.getClass().getName();
        String msg = "%s size = %d" + LogzConstant.BR;
        msg = String.format(msg, simpleName, collection.size());
        if (!collection.isEmpty()) {
            Iterator iterator = collection.iterator();
            int flag = 0;
            while (iterator.hasNext()) {
                String itemString = "[%d]:%s%s";
                Object item = iterator.next();
                msg += String.format(itemString, flag, LogzConvert.objectToString(item),
                        flag++ < collection.size() - 1 ? "," + LogzConstant.BR : LogzConstant.BR);
            }
        }
        return msg;
    }
}
