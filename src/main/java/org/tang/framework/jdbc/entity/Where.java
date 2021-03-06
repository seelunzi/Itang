package org.tang.framework.jdbc.entity;

import lombok.Data;
import org.tang.framework.entity.BaseModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * @author tang
 * */

@SuppressWarnings("serial")
public class Where extends BaseModel {
    private List<ThisWhere> wheres = new ArrayList<ThisWhere>();

    /**
     * 设置参数
     * @param fieldName   字段名
     * @param symbol      操作符
     * @param fieldValues 字段值
     */
    public Where set(String fieldName, String symbol, Object... fieldValues) {
        ThisWhere thisWhere = new ThisWhere();
        thisWhere.setFieldName(fieldName);
        thisWhere.setSymbol(symbol);
        if (fieldValues != null) {
            List<Object> values = new ArrayList<Object>();
            for (Object obj : fieldValues) {
                if (obj instanceof Collection<?>) {
                    for (Object o : (Collection<?>) obj) {
                        values.add(o);
                    }
                    continue;
                }
                if (obj.getClass().isArray()) {
                    for (Object o : (Object[]) obj) {
                        values.add(o);
                    }
                    continue;
                }
                values.add(obj);
            }
            if (values.size() > 1) {
                thisWhere.setSymbol("in");
            }
            thisWhere.setFieldValues(values);
        }
        wheres.add(thisWhere);
        return this;
    }

    /**
     * 设置参数，默认条件为等于
     *
     * @param fieldName  字段名
     * @param fieldValue 字段值
     */
    public Where set(String fieldName, Object fieldValue) {
        return set(fieldName, "=", fieldValue);
    }

    public List<ThisWhere> getWheres() {
        return wheres;
    }

    public void setWheres(List<ThisWhere> wheres) {
        this.wheres = wheres;
    }

    public void clear() {
        wheres.clear();
    }

    @Data
    public static class ThisWhere {
        private String fieldName;
        private String symbol;
        private List<Object> fieldValues;
    }

}

