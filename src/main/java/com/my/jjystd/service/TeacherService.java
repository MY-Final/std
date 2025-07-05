package com.my.jjystd.service;

import com.my.jjystd.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    /**
     * 根据ID查询教师信息
     * @param id 教师ID
     * @return 教师信息
     */
    Optional<Teacher> findTeacherById(Integer id);
    
    /**
     * 根据教师编号查询教师信息
     * @param teacherNo 教师编号
     * @return 教师信息
     */
    Optional<Teacher> findTeacherByTeacherNo(String teacherNo);
    
    /**
     * 根据用户ID查询教师信息
     * @param userId 用户ID
     * @return 教师信息
     */
    Optional<Teacher> findTeacherByUserId(Integer userId);
    
    /**
     * 获取所有教师列表
     * @return 教师列表
     */
    List<Teacher> findAllTeachers();
    
    /**
     * 分页获取所有教师列表
     * @param pageable 分页参数
     * @return 分页教师列表
     */
    Page<Teacher> findAllTeachers(Pageable pageable);
    
    /**
     * 新增教师信息
     * @param teacher 教师信息
     * @return 保存后的教师信息
     */
    Teacher saveTeacher(Teacher teacher);
    
    /**
     * 更新教师信息
     * @param id 教师ID
     * @param teacher 更新的教师信息
     * @return 更新后的教师信息
     */
    Optional<Teacher> updateTeacher(Integer id, Teacher teacher);
    
    /**
     * 删除教师信息
     * @param id 教师ID
     * @return 是否删除成功
     */
    boolean deleteTeacher(Integer id);
} 