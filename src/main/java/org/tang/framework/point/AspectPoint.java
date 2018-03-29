package org.tang.framework.point;

import lombok.Data;
import net.sf.cglib.proxy.MethodProxy;
import org.tang.framework.entity.BaseModel;

import java.lang.reflect.Method;

@SuppressWarnings("serial")
@Data
public class AspectPoint extends BaseModel {

    private Object bean;
    private Method method;
    private MethodProxy proxy;
    private Class<?> clazz;
    private Object[] params;
    private Method currentAspectMethod;
    private AspectPoint childAspect;

    public Object invoke() throws Throwable {
        if (childAspect != null) {
            return childAspect.getCurrentAspectMethod().invoke(bean, childAspect);
        }
        return proxy.invokeSuper(bean, params);
    }
}
