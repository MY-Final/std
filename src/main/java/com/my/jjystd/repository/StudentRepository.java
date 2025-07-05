package com.my.jjystd.repository;

import com.my.jjystd.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    // 继承了JpaRepository，已经包含了基础的CRUD操作，如findById等
    
    /**
     * 根据学号查询学生
     * @param studentNo 学号
     * @return 学生信息
     */
    Optional<Student> findByStudentNo(String studentNo);
    
    /**
     * 根据用户ID查询学生
     * @param userId 用户ID
     * @return 学生信息
     */
    Optional<Student> findByUserId(Integer userId);
} 