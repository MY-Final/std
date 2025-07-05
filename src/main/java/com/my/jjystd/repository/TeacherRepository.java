package com.my.jjystd.repository;

import com.my.jjystd.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    // 继承了JpaRepository，已经包含了基础的CRUD操作，如findById等
    
    /**
     * 根据教师编号查询教师
     * @param teacherNo 教师编号
     * @return 教师信息
     */
    Optional<Teacher> findByTeacherNo(String teacherNo);
    
    /**
     * 根据用户ID查询教师
     * @param userId 用户ID
     * @return 教师信息
     */
    Optional<Teacher> findByUserId(Integer userId);
} 