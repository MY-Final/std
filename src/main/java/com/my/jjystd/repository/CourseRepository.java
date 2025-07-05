package com.my.jjystd.repository;

import com.my.jjystd.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course> {
    /**
     * 根据课程名称查询课程
     * @param name 课程名称
     * @return 课程列表
     */
    List<Course> findByNameContaining(String name);
    
    /**
     * 分页根据课程名称查询课程
     * @param name 课程名称
     * @param pageable 分页参数
     * @return 分页课程列表
     */
    Page<Course> findByNameContaining(String name, Pageable pageable);
    
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
    
    /**
     * 分页根据教师ID查询课程
     * @param teacherId 教师ID
     * @param pageable 分页参数
     * @return 分页课程列表
     */
    Page<Course> findByTeacherId(Integer teacherId, Pageable pageable);
} 