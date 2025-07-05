package com.my.jjystd.service;

import com.my.jjystd.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    /**
     * 根据ID查询学生信息
     * @param id 学生ID
     * @return 学生信息
     */
    Optional<Student> findStudentById(Integer id);
    
    /**
     * 根据学号查询学生信息
     * @param studentNo 学号
     * @return 学生信息
     */
    Optional<Student> findStudentByStudentNo(String studentNo);
    
    /**
     * 根据用户ID查询学生信息
     * @param userId 用户ID
     * @return 学生信息
     */
    Optional<Student> findStudentByUserId(Integer userId);
    
    /**
     * 获取所有学生列表
     * @return 学生列表
     */
    List<Student> findAllStudents();
    
    /**
     * 新增学生信息
     * @param student 学生信息
     * @return 保存后的学生信息
     */
    Student saveStudent(Student student);
    
    /**
     * 更新学生信息
     * @param id 学生ID
     * @param student 更新的学生信息
     * @return 更新后的学生信息
     */
    Optional<Student> updateStudent(Integer id, Student student);
    
    /**
     * 删除学生信息
     * @param id 学生ID
     * @return 是否删除成功
     */
    boolean deleteStudent(Integer id);
} 