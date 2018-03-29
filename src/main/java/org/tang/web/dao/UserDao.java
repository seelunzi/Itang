package org.tang.web.dao;

import org.tang.web.domain.UserInfo;

import java.util.List;

/***
 * @author tang
 * */
public interface UserDao {

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
     * 查询用户信息
     */
    UserInfo getUserInfo(String userId);

    /**
     * 删除用户
     *
     * @param userId
     */
    void deleteUser(String userId);

}
