package org.tang.framework.aspect.entity;

import lombok.Data;
import org.tang.framework.entity.BaseModel;

import java.lang.reflect.Method;

/***
 * @author tang
 * */
@SuppressWarnings("serial")
@Data
public class AspectEntity extends BaseModel {

    private Class<?>[] annotationClass;

    private String methodMappath;

    private String classMappath;

    private Method aspectInvokeMethod;
}
