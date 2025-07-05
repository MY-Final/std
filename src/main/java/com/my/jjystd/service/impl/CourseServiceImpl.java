package com.my.jjystd.service.impl;

import com.my.jjystd.entity.Course;
import com.my.jjystd.repository.CourseRepository;
import com.my.jjystd.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;
    
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    @Override
    public Optional<Course> findCourseById(Integer id) {
        return courseRepository.findById(id);
    }
    
    @Override
    public List<Course> findCoursesByName(String name) {
        return courseRepository.findByNameContaining(name);
    }
    
    @Override
    public Course findByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }
    
    @Override
    public List<Course> findCoursesByTeacherId(Integer teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }
    
    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
    
    @Override
    public Course saveCourse(Course course) {
        // 设置创建时间
        if (course.getCreateTime() == null) {
            course.setCreateTime(new Date());
        }
        return courseRepository.save(course);
    }
    
    @Override
    public Optional<Course> updateCourse(Integer id, Course courseDetails) {
        return courseRepository.findById(id).map(existingCourse -> {
            // 更新课程信息，但保留ID和创建时间
            if (courseDetails.getName() != null) {
                existingCourse.setName(courseDetails.getName());
            }
            if (courseDetails.getCourseCode() != null) {
                existingCourse.setCourseCode(courseDetails.getCourseCode());
            }
            if (courseDetails.getDescription() != null) {
                existingCourse.setDescription(courseDetails.getDescription());
            }
            if (courseDetails.getTeacher() != null) {
                existingCourse.setTeacher(courseDetails.getTeacher());
            }
            if (courseDetails.getCredit() != null) {
                existingCourse.setCredit(courseDetails.getCredit());
            }
            if (courseDetails.getStatus() != null) {
                existingCourse.setStatus(courseDetails.getStatus());
            }
            // 保存更新后的课程信息
            return courseRepository.save(existingCourse);
        });
    }
    
    @Override
    public boolean deleteCourse(Integer id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            return true;
        }).orElse(false);
    }
} 