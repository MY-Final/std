package com.my.jjystd.repository;

import com.my.jjystd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    Optional<User> findByUsername(String username);
} 