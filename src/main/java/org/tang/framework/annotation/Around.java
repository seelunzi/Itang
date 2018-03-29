package org.tang.framework.annotation;

import java.lang.annotation.*;

/**
 * @author tang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Arounds.class)
public @interface Around {

    Class<?>[] annotationClass() default {};

    String methodMappath() default "";

    String classMappath() default "";

}
