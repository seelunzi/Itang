package org.tang.framework.constant;

import org.tang.framework.annotation.InitBean;
import org.tang.framework.annotation.PathBinding;
import org.tang.framework.aspect.entity.AspectEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FrameworkConstant {

    /**
     * 初始化Bean拦截的注解
     */
    public static final Class<?>[] beanAnnotations = new Class[]{InitBean.class, PathBinding.class};

    /**
     * 切面存储。key为切面ID，Value为切面实例
     */
    public static final Map<String, AspectEntity> aspectMap = new ConcurrentHashMap<String, AspectEntity>();

    /**
     * 表主键列表
     */
    public static final String table_primary_keys = "table_primary_keys";

    /**
     * 自动化缓存KEY
     */
    public static final String AUTO_CACHE_KEY = "AUTO_CACHE_KEY";


}
