package org.tang.framework.container;

import org.tang.framework.aspect.CacheAspect;
import org.tang.framework.aspect.LoggerAspect;
import org.tang.framework.aspect.TransactedAspect;

/***
 * @author tang
 * */
public class BuiltContainer {
    public static final Class<?>[] initAspect = {CacheAspect.class, TransactedAspect.class, LoggerAspect.class};
}
