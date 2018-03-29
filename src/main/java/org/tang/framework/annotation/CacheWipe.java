package org.tang.framework.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(CacheWipes.class)
public @interface CacheWipe {

    String key();

    String[] fields() default "";

}
