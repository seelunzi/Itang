package org.tang.framework.container;

import java.util.HashMap;
import java.util.Map;

/***
 * @author tang
 * */
public class ThreadContainer {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static <T> T get(String fieldName) {
        initThreadContainer();
        return (T) threadLocal.get().get(fieldName);
    }

    public static void initThreadContainer() {
        if (threadLocal.get() != null) {
            return;
        }
        threadLocal.set(new HashMap<String, Object>(16));
    }

    public static void set(String fieldName, Object value) {
        initThreadContainer();
        threadLocal.get().put(fieldName, value);
    }

    public static boolean containsKey(String fieldName) {
        initThreadContainer();
        return threadLocal.get().containsKey(fieldName);
    }

}
