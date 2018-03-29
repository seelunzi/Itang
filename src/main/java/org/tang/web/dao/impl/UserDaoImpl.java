package org.tang.web.dao.impl;

import org.tang.framework.annotation.InitBean;
import org.tang.framework.iface.InitFace;
import org.tang.framework.util.JUUIDUtil;
import org.tang.framework.util.StringUtil;
import org.tang.web.dao.UserDao;
import org.tang.web.domain.UserInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 * @author tang
 * */
@InitBean
public class UserDaoImpl implements UserDao, InitFace {

    private static final Map<String, UserInfo> dataMap = new ConcurrentHashMap<String, UserInfo>();

    /**
     * 保存或更新用户信息
     *
     * @param user
     */
    @Override
    public void saveOrUpdateUser(UserInfo user) {
        if (StringUtil.isNullOrEmpty(user.getUserId())) {
            user.setUserId(JUUIDUtil.createUuid());
            dataMap.put(user.getUserId(), user);
            return;
        }
        dataMap.put(user.getUserId(), user);
    }

    /**
     * 查询用户列表
     */
    @Override
    public List<UserInfo> getUsers() {
        Collection<UserInfo> users = dataMap.values();
        return new ArrayList<UserInfo>(users);
    }

    /**
     * 查询用户信息
     */
    @Override
    public UserInfo getUserInfo(String userId) {
        return dataMap.get(userId);
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @Override
    public void deleteUser(String userId) {
        dataMap.remove(userId);
    }

    /**
     * bean加载时执行
     */
    @Override
    public void init() {
        UserInfo user = new UserInfo();
        user.setAge(18);
        user.setUserId(JUUIDUtil.createUuid());
        user.setUserName("张三");
        dataMap.put(user.getUserId(), user);
        user = new UserInfo();
        user.setAge(19);
        user.setUserId(JUUIDUtil.createUuid());
        user.setUserName("李四");
        dataMap.put(user.getUserId(), user);
        user = new UserInfo();
        user.setAge(20);
        user.setUserId(JUUIDUtil.createUuid());
        user.setUserName("王五");
        dataMap.put(user.getUserId(), user);
        user = new UserInfo();
        user.setAge(21);
        user.setUserId(JUUIDUtil.createUuid());
        user.setUserName("马六");
        dataMap.put(user.getUserId(), user);
    }
}
