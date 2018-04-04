package org.tang.framework.adapt;

import org.tang.framework.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 参数适配器
 *
 * @author admin
 */
public class ParamsAdapt {

    /**
     * 参数自动装载，目前支持简单的HTTP参数
     *
     * @param paramTypes
     * @param paras
     * @param request
     * @param response
     * @param session
     * @return
     */
    public static Object[] adaptParams(Class<?>[] paramTypes, Map<String, Object> paras, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if (StringUtil.isNullOrEmpty(paramTypes)) {
            return null;
        }
        if (StringUtil.isNullOrEmpty(paras)) {
            Object[] params = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> paraType = paramTypes[i];
                if (paraType.isAssignableFrom(request.getClass())) {
                    params[i] = request;
                    continue;
                }
                if (paraType.isAssignableFrom(response.getClass())) {
                    params[i] = response;
                    continue;
                }
                if (paraType.isAssignableFrom(session.getClass())) {
                    params[i] = session;
                    continue;
                }
            }
            return params;
        }
        return new Object[paramTypes.length];
    }
}
