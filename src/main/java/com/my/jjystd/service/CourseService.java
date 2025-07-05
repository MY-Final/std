package com.my.jjystd.service;

import com.my.jjystd.controller.dto.CourseQueryDTO;
import com.my.jjystd.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    /**
     * 根据ID查询课程信息
     * @param id 课程ID
     * @return 课程信息
     */
    Optional<Course> findCourseById(Integer id);
    
    /**
     * 根据课程名称查询课程
     * @param name 课程名称
     * @return 课程列表
     */
    List<Course> findCoursesByName(String name);
    
    /**
     * 分页根据课程名称查询课程
     * @param name 课程名称
     * @param pageable 分页参数
     * @return 分页课程列表
     */
    Page<Course> findCoursesByName(String name, Pageable pageable);
    
    /**
     * 根据课程代码查询课程
     * @param courseCode 课程代码
     * @return 课程
     */
    Course findByCourseCode(String courseCode);
    
    /**
     * 根据教师ID查询课程
     * @param teacherId 教师ID
     * @return 课程列表
     */
    List<Course> findCoursesByTeacherId(Integer teacherId);
    
    /**
     * 分页根据教师ID查询课程
     * @param teacherId 教师ID
     * @param pageable 分页参数
     * @return 分页课程列表
     */
    Page<Course> findCoursesByTeacherId(Integer teacherId, Pageable pageable);
    
    /**
     * 动态模糊查询课程信息
     * @param queryDTO 查询条件
     * @return 符合条件的课程列表
     */
    List<Course> findCoursesByCondition(CourseQueryDTO queryDTO);
    
    /**
     * 分页动态模糊查询课程信息
     * @param queryDTO 查询条件
     * @param pageable 分页参数
     * @return 分页查询结果
     */
    Page<Course> findCoursesByCondition(CourseQueryDTO queryDTO, Pageable pageable);
    
    /**
     * 获取所有课程
     * @return 课程列表
     */
    List<Course> findAllCourses();
    
    /**
     * 分页获取所有课程
     * @param pageable 分页参数
     * @return 分页课程列表
     */
    Page<Course> findAllCourses(Pageable pageable);
    
    /**
     * 新增课程
     * @param course 课程信息
     * @return 保存后的课程信息
     */
    Course saveCourse(Course course);
    
    /**
     * 更新课程信息
     * @param id 课程ID
     * @param course 更新的课程信息
     * @return 更新后的课程信息
     */
    Optional<Course> updateCourse(Integer id, Course course);
    
    /**
     * 删除课程
     * @param id 课程ID
     * @return 是否删除成功
     */
    boolean deleteCourse(Integer id);
} 