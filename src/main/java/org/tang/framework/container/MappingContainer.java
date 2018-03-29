package org.tang.framework.container;

import lombok.Data;
import org.tang.framework.entity.BaseModel;
import org.tang.framework.entity.BeanEntity;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @author tang
 * */
@SuppressWarnings("unchecked")
public class MappingContainer {

    private static Map<String, Object> mvcMap = new ConcurrentHashMap<String, Object>();

    public static <T> T getMapping(String path) {
        return (T) mvcMap.get(path);
    }

    public static void writeMapping(MvcMapping mvcMapping) {
        mvcMap.put(mvcMapping.getPath(), mvcMapping);
    }

    public static boolean containsPath(String path) {
        return mvcMap.containsKey(path);
    }

    public static Collection<?> getBeans() {
        return mvcMap.values();
    }

    @SuppressWarnings("serial")
    @Data
    public static class MvcMapping extends BaseModel {

        private String path;

        private Method method;

        private Object bean;

        private List<BeanEntity> paramTypes;
    }
}
