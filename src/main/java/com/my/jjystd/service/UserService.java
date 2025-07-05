package com.my.jjystd.service;

import com.my.jjystd.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * 根据ID查询用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    Optional<User> findUserById(Integer id);
    
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户信息
     */
    Optional<User> findUserByUsername(String username);
    
    /**
     * 获取所有用户列表
     * @return 用户列表
     */
    List<User> findAllUsers();
    
    /**
     * 新增用户
     * @param user 用户信息
     * @return 保存后的用户信息
     */
    User saveUser(User user);
    
    /**
     * 更新用户信息
     * @param id 用户ID
     * @param user 更新的用户信息
     * @return 更新后的用户信息
     */
    Optional<User> updateUser(Integer id, User user);
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    boolean deleteUser(Integer id);
    
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户信息，失败返回空
     */
    Optional<User> login(String username, String password);
} 