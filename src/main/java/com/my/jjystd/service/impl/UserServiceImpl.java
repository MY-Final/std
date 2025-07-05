package com.my.jjystd.service.impl;

import com.my.jjystd.entity.User;
import com.my.jjystd.repository.UserRepository;
import com.my.jjystd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }
    
    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    
    @Override
    public User saveUser(User user) {
        // 设置创建时间和默认激活状态
        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
        if (user.getActive() == null) {
            user.setActive(true); // 默认激活
        }
        return userRepository.save(user);
    }
    
    @Override
    public Optional<User> updateUser(Integer id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            // 更新用户信息，但保留ID和创建时间
            if (userDetails.getUsername() != null) {
                existingUser.setUsername(userDetails.getUsername());
            }
            if (userDetails.getPassword() != null) {
                existingUser.setPassword(userDetails.getPassword());
            }
            if (userDetails.getRole() != null) {
                existingUser.setRole(userDetails.getRole());
            }
            if (userDetails.getPhone() != null) {
                existingUser.setPhone(userDetails.getPhone());
            }
            if (userDetails.getEmail() != null) {
                existingUser.setEmail(userDetails.getEmail());
            }
            if (userDetails.getActive() != null) {
                existingUser.setActive(userDetails.getActive());
            }
            // 保存更新后的用户信息
            return userRepository.save(existingUser);
        });
    }
    
    @Override
    public boolean deleteUser(Integer id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }
    
    @Override
    public Optional<User> login(String username, String password) {
        // 根据用户名查询用户
        return userRepository.findByUsername(username)
                .filter(user -> 
                    // 验证密码是否匹配
                    user.getPassword().equals(password) &&
                    // 验证用户是否激活
                    user.getActive() != null && user.getActive()
                );
    }
} 