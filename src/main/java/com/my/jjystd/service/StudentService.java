package com.my.jjystd.service;

import com.my.jjystd.controller.dto.StudentQueryDTO;
import com.my.jjystd.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 分页获取所有学生列表
     * @param pageable 分页参数
     * @return 分页学生列表
     */
    Page<Student> findAllStudents(Pageable pageable);
    
    /**
     * 动态模糊查询学生信息
     * @param queryDTO 查询条件
     * @return 符合条件的学生列表
     */
    List<Student> findStudentsByCondition(StudentQueryDTO queryDTO);
    
    /**
     * 分页动态模糊查询学生信息
     * @param queryDTO 查询条件
     * @param pageable 分页参数
     * @return 分页查询结果
     */
    Page<Student> findStudentsByCondition(StudentQueryDTO queryDTO, Pageable pageable);
    
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