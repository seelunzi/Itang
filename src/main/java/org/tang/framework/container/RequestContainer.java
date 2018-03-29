package org.tang.framework.container;

import org.tang.framework.constant.GeneralFinal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestContainer {


    public static void setRequest(HttpServletRequest request) {
        ThreadContainer.set(GeneralFinal.REQUEST_WRAPPER, request);
    }

    public static HttpServletRequest getRequest() {
        return ThreadContainer.get(GeneralFinal.REQUEST_WRAPPER);
    }

    public static void setResponse(HttpServletResponse response) {
        ThreadContainer.set(GeneralFinal.RESPONSE_WRAPPER, response);
    }

    public static HttpServletResponse getResponse() {
        return ThreadContainer.get(GeneralFinal.RESPONSE_WRAPPER);
    }
}
