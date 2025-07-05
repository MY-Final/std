package com.my.jjystd.service.impl;

import com.my.jjystd.controller.dto.CourseQueryDTO;
import com.my.jjystd.entity.Course;
import com.my.jjystd.repository.CourseRepository;
import com.my.jjystd.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
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
    public Page<Course> findCoursesByName(String name, Pageable pageable) {
        return courseRepository.findByNameContaining(name, pageable);
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
    public Page<Course> findCoursesByTeacherId(Integer teacherId, Pageable pageable) {
        return courseRepository.findByTeacherId(teacherId, pageable);
    }
    
    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
    
    @Override
    public Page<Course> findAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
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
    
    @Override
    public List<Course> findCoursesByCondition(CourseQueryDTO queryDTO) {
        Specification<Course> spec = buildSpecification(queryDTO);
        return courseRepository.findAll(spec);
    }
    
    @Override
    public Page<Course> findCoursesByCondition(CourseQueryDTO queryDTO, Pageable pageable) {
        Specification<Course> spec = buildSpecification(queryDTO);
        return courseRepository.findAll(spec, pageable);
    }
    
    /**
     * 构建动态查询条件
     * @param queryDTO 查询参数
     * @return 查询条件Specification
     */
    private Specification<Course> buildSpecification(CourseQueryDTO queryDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // 根据课程名称模糊查询
            if (StringUtils.hasText(queryDTO.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + queryDTO.getName() + "%"));
            }
            
            // 根据课程代码模糊查询
            if (StringUtils.hasText(queryDTO.getCourseCode())) {
                predicates.add(criteriaBuilder.like(root.get("courseCode"), "%" + queryDTO.getCourseCode() + "%"));
            }
            
            // 根据课程描述模糊查询
            if (StringUtils.hasText(queryDTO.getDescription())) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + queryDTO.getDescription() + "%"));
            }
            
            // 根据教师ID精确查询
            if (queryDTO.getTeacherId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("teacher").get("id"), queryDTO.getTeacherId()));
            }
            
            // 根据教师姓名模糊查询
            if (StringUtils.hasText(queryDTO.getTeacherName())) {
                Join<Object, Object> teacherJoin = root.join("teacher", JoinType.INNER);
                predicates.add(criteriaBuilder.like(teacherJoin.get("name"), "%" + queryDTO.getTeacherName() + "%"));
            }
            
            // 根据学分精确查询
            if (queryDTO.getCredit() != null) {
                predicates.add(criteriaBuilder.equal(root.get("credit"), queryDTO.getCredit()));
            }
            
            // 根据状态精确查询
            if (queryDTO.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), queryDTO.getStatus()));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
} 