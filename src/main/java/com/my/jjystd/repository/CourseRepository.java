package com.my.jjystd.repository;

import com.my.jjystd.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    /**
     * 根据课程名称查询课程
     * @param name 课程名称
     * @return 课程列表
     */
    List<Course> findByNameContaining(String name);
    
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
    List<Course> findByTeacherId(Integer teacherId);
} 