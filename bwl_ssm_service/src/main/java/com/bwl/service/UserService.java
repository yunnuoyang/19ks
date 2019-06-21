package com.bwl.service;


import com.bwl.pojo.User;

import java.util.List;

/**
 * 对用户进行增删改查的业务层
 */

public interface UserService {
    /**
     * 查询所有的用户
     *
     * @return
     */
    List<User> findAllUser();

    /**
     * 删除用户，根据用户的id
     *
     * @param user 封装用户id
     * @return 返回boolean值进行判定
     */
    boolean deleteUserById(User user);

    /**
     * 增加用户
     * @param user 封装用户信息
     */
    boolean addUser(User user);

    /**
     * 修改用户
     * @param user 用户实体
     * @return 影响行数
     */
    Integer updateUser(User user);

    /**
     * 根据id查询用户的方法
     * @param user
     * @return
     */
    User getUser(User user);

    User getUserByNum(String username);
}
