package org.tang.framework.aspect;

import org.tang.framework.annotation.Around;
import org.tang.framework.annotation.InitBean;
import org.tang.framework.annotation.Transacted;
import org.tang.framework.container.TransactedThreadContainer;
import org.tang.framework.point.AspectPoint;
import org.tang.framework.util.StringUtil;

import java.sql.Connection;
import java.util.List;

/***
 * @author tang
 * */
@InitBean
public class TransactedAspect {

    /**
     * 事物控制
     * @param wrapper
     * @return
     * @throws Throwable
     */
    @Around(annotationClass = Transacted.class)
    public Object transacted(AspectPoint wrapper) throws Throwable {
        if (TransactedThreadContainer.hasTransacted()) {
            return wrapper.invoke();
        }
        try {
            TransactedThreadContainer.writeHasTransacted();
            Object result = wrapper.invoke();
            //提交事物
            List<Connection> connections = TransactedThreadContainer.getConnections();
            if (!StringUtil.isNullOrEmpty(connections)) {
                for (Connection conn : connections) {
                    try {
                        conn.commit();
                    } catch (Exception e) {
                    }
                }
            }
            return result;
        } finally {
            //关闭连接
            List<Connection> connections = TransactedThreadContainer.getConnections();
            if (!StringUtil.isNullOrEmpty(connections)) {
                for (Connection conn : connections) {
                    try {
                        conn.close();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }
    }
}
