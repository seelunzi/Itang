package org.tang.web.service.impl;

import org.tang.framework.annotation.CacheWipe;
import org.tang.framework.annotation.CacheWrite;
import org.tang.framework.annotation.InitBean;
import org.tang.framework.annotation.OutBean;
import org.tang.web.comm.constant.CacheFinal;
import org.tang.web.dao.UserDao;
import org.tang.web.domain.UserInfo;
import org.tang.web.service.UserService;

import java.util.List;

@InitBean
public class UserServiceImpl implements UserService {

    @OutBean
    UserDao userDao;


    /**
     * 保存或更新用户信息
     *
     * @param user
     */
    @CacheWipe(key = CacheFinal.USER_INFO, fields = "user.userId")
    @CacheWipe(key = CacheFinal.USER_LIST)
    public void saveOrUpdateUser(UserInfo user) {
        userDao.saveOrUpdateUser(user);
    }

    /**
     * 查询用户列表
     */
    @CacheWrite(key = CacheFinal.USER_LIST, time = 3600)
    public List<UserInfo> getUsers() {
        return userDao.getUsers();
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @CacheWipe(key = CacheFinal.USER_INFO, fields = "user.userId")
    @CacheWipe(key = CacheFinal.USER_LIST)
    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }

    /**
     * 查询用户信息
     */
    @CacheWrite(key = CacheFinal.USER_INFO, fields = "userId")
    public UserInfo getUserInfo(String userId) {
        return userDao.getUserInfo(userId);
    }
}
