package com.my.jjystd.service;

import com.my.jjystd.entity.Course;
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
     * 获取所有课程
     * @return 课程列表
     */
    List<Course> findAllCourses();
    
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