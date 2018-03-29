package org.tang.web.controller;

import com.alibaba.fastjson.JSON;
import org.tang.framework.annotation.JsonSerialize;
import org.tang.framework.annotation.OutBean;
import org.tang.framework.annotation.PathBinding;
import org.tang.web.comm.entity.MsgEntity;
import org.tang.web.domain.UserInfo;
import org.tang.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 测试Controller
 *
 * @author admin
 */
@PathBinding("/")
public class GeneralController {

    @OutBean
    UserService userService;

    @PathBinding("/index.do")
    @JsonSerialize
    public Object index(HttpServletRequest request) {
        List<UserInfo> users = userService.getUsers();
        return users;
    }

    @PathBinding("/test.do")
    @JsonSerialize
    public Object test(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(JSON.toJSONString(request.getParameterMap()));
        return new MsgEntity(0, "操作成功", "这是test的内容");
    }
}
