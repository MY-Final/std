package com.my.jjystd.config;

import com.my.jjystd.entity.User;
import com.my.jjystd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * 系统启动时初始化超级管理员账号
 */
@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public AdminInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        // 检查是否已存在超级管理员账号
        Optional<User> adminOpt = userRepository.findByUsername("admin");
        
        if (adminOpt.isEmpty()) {
            // 创建超级管理员账号
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123"); // 初始密码，建议在首次登录后修改
            admin.setRole("admin");
            admin.setActive(true);
            admin.setCreateTime(new Date());
            admin.setEmail("admin@system.com");
            
            // 保存超级管理员账号
            userRepository.save(admin);
            
            System.out.println("超级管理员账号已初始化，用户名：admin，密码：admin123");
        }
    }
} 