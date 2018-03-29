package org.tang.framework.cache;

import org.tang.framework.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @className：CacheHandler
 * @description：缓存操作类，对缓存进行管理,清除方式采用Timer定时的方式
 * @creater：Coody
 * @creatTime：2014年5月7日 上午9:18:54
 * @remark：
 */
@SuppressWarnings("unchecked")
public class LocalCache {

    private static final ScheduledExecutorService scheduledExecutorService;
    private static final ConcurrentHashMap<String, Object> map;
    static Object mutex = new Object();

    static {
        scheduledExecutorService = Executors.newScheduledThreadPool(6);
        map = new ConcurrentHashMap<String, Object>();
    }

    /**
     * 增加缓存对象
     *
     * @param key
     * @param ce
     * @param validityTime 有效时间
     */
    public static void setCache(String key, Object ce,
                                int validityTime) {
        map.put(key, new CacheWrapper(validityTime, ce));
        scheduledExecutorService.schedule(() -> {
            CacheWrapper cacheWrapper = (CacheWrapper) map.get(key);
            if (cacheWrapper == null || cacheWrapper.getDate() == null) {
                return;
            }
            if (System.currentTimeMillis() < cacheWrapper.getDate().getTime()) {
                return;
            }
            LocalCache.delCache(key);
        }, validityTime * 1000, TimeUnit.DAYS);
    }

    /**
     * 获取缓存KEY列表
     */
    public static Set<String> getCacheKeys() {
        return map.keySet();
    }

    public static List<String> getKeysFuzz(String patton) {
        List<String> list = new ArrayList<String>();
        for (String tmpKey : map.keySet()) {
            if (tmpKey.contains(patton)) {
                list.add(tmpKey);
            }
        }
        if (StringUtil.isNullOrEmpty(list)) {
            return null;
        }
        return list;
    }

    public static Integer getKeySizeFuzz(String patton) {
        Integer num = 0;
        for (String tmpKey : map.keySet()) {
            if (tmpKey.startsWith(patton)) {
                num++;
            }
        }
        return num;
    }

    /**
     * 增加缓存对象
     *
     * @param key
     * @param ce
     */
    public static void setCache(String key, Object ce) {
        map.put(key, new CacheWrapper(ce));
    }

    /**
     * 获取缓存对象
     *
     * @param key
     * @return
     */
    public static <T> T getCache(String key) {
        CacheWrapper wrapper = (CacheWrapper) map.get(key);
        if (wrapper == null) {
            return null;
        }
        return (T) wrapper.getValue();
    }

    /**
     * 检查是否含有制定key的缓冲
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return map.containsKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static void delCache(String key) {
        map.remove(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static void delCacheFuzz(String key) {
        for (String tmpKey : map.keySet()) {
            if (tmpKey.contains(key)) {
                map.remove(tmpKey);
            }
        }
    }

    /**
     * 获取缓存大小
     *
     */
    public static int getCacheSize() {
        return map.size();
    }

    /**
     * 清除全部缓存
     */
    public static void clearCache() {
        map.clear();
    }


    private static class CacheWrapper {
        private Date date;
        private Object value;

        public CacheWrapper(int time, Object value) {
            this.date = new Date(System.currentTimeMillis() + time * 1000);
            this.value = value;
        }

        public CacheWrapper(Object value) {
            this.value = value;
        }

        public Date getDate() {
            return date;
        }

        public Object getValue() {
            return value;
        }
    }
}
