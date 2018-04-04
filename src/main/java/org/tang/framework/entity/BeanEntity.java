package org.tang.framework.entity;

import lombok.Data;
import org.tang.framework.util.StringUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/***
 * @author tang
 * */
@SuppressWarnings("serial")
@Data
public class BeanEntity extends BaseModel {

    private String fieldName;
    private Object fieldValue;
    private Class<?> fieldType;
    private Annotation[] fieldAnnotations;
    private Field sourceField;

    public Annotation getAnnotation(Class<?> clazz) {
        if (StringUtil.isNullOrEmpty(fieldAnnotations)) {
            return null;
        }
        for (Annotation annotation : fieldAnnotations) {
            if (clazz.isAssignableFrom(annotation.annotationType())) {
                return annotation;
            }
        }
        return null;
    }
}
