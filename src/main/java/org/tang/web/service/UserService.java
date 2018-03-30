package org.tang.web.service;

import org.tang.web.domain.UserInfo;

import java.util.List;

public interface UserService {

    /**
     * 保存或更新用户信息
     *
     * @param user
     */
    void saveOrUpdateUser(UserInfo user);

    /**
     * 查询用户列表
     */
    List<UserInfo> getUsers();

    /**
     * 删除用户
     *
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * 查询用户信息
     */
    UserInfo getUserInfo(String userId);
}
