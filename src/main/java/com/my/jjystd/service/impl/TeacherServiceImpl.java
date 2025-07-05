package com.my.jjystd.service.impl;

import com.my.jjystd.controller.dto.TeacherQueryDTO;
import com.my.jjystd.entity.Teacher;
import com.my.jjystd.entity.User;
import com.my.jjystd.repository.TeacherRepository;
import com.my.jjystd.service.TeacherService;
import com.my.jjystd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class TeacherServiceImpl implements TeacherService {
    
    private final TeacherRepository teacherRepository;
    private final UserService userService;
    
    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, UserService userService) {
        this.teacherRepository = teacherRepository;
        this.userService = userService;
    }
    
    @Override
    public Optional<Teacher> findTeacherById(Integer id) {
        return teacherRepository.findById(id);
    }
    
    @Override
    public Optional<Teacher> findTeacherByTeacherNo(String teacherNo) {
        return teacherRepository.findByTeacherNo(teacherNo);
    }
    
    @Override
    public Optional<Teacher> findTeacherByUserId(Integer userId) {
        return teacherRepository.findByUserId(userId);
    }
    
    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }
    
    @Override
    public Page<Teacher> findAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }
    
    @Override
    public List<Teacher> findTeachersByCondition(TeacherQueryDTO queryDTO) {
        Specification<Teacher> spec = buildSpecification(queryDTO);
        return teacherRepository.findAll(spec);
    }
    
    @Override
    public Page<Teacher> findTeachersByCondition(TeacherQueryDTO queryDTO, Pageable pageable) {
        Specification<Teacher> spec = buildSpecification(queryDTO);
        return teacherRepository.findAll(spec, pageable);
    }
    
    /**
     * 构建动态查询条件
     * @param queryDTO 查询参数
     * @return 查询条件Specification
     */
    private Specification<Teacher> buildSpecification(TeacherQueryDTO queryDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // 根据姓名模糊查询
            if (StringUtils.hasText(queryDTO.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + queryDTO.getName() + "%"));
            }
            
            // 根据教师编号模糊查询
            if (StringUtils.hasText(queryDTO.getTeacherNo())) {
                predicates.add(criteriaBuilder.like(root.get("teacherNo"), "%" + queryDTO.getTeacherNo() + "%"));
            }
            
            // 根据性别精确查询
            if (StringUtils.hasText(queryDTO.getGender())) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), queryDTO.getGender()));
            }
            
            // 根据职称模糊查询
            if (StringUtils.hasText(queryDTO.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + queryDTO.getTitle() + "%"));
            }
            
            // 根据院系模糊查询
            if (StringUtils.hasText(queryDTO.getDepartment())) {
                predicates.add(criteriaBuilder.like(root.get("department"), "%" + queryDTO.getDepartment() + "%"));
            }
            
            // 根据电话模糊查询
            if (StringUtils.hasText(queryDTO.getPhone())) {
                predicates.add(criteriaBuilder.like(root.get("phone"), "%" + queryDTO.getPhone() + "%"));
            }
            
            // 根据邮箱模糊查询
            if (StringUtils.hasText(queryDTO.getEmail())) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + queryDTO.getEmail() + "%"));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    
    @Override
    @Transactional
    public Teacher saveTeacher(Teacher teacher) {
        // 设置创建时间
        if (teacher.getCreateTime() == null) {
            teacher.setCreateTime(new Date());
        }
        
        // 自动创建用户账号
        if (teacher.getUserId() == null && teacher.getTeacherNo() != null) {
            // 创建用户对象
            User user = new User();
            user.setUsername(teacher.getTeacherNo()); // 使用教师编号作为用户名
            user.setPassword("123456"); // 默认密码
            user.setRole("teacher"); // 角色为教师
            user.setPhone(teacher.getPhone());
            user.setEmail(teacher.getEmail());
            user.setActive(true);
            
            // 保存用户并获取用户ID
            User savedUser = userService.saveUser(user);
            teacher.setUserId(savedUser.getId());
        }
        
        return teacherRepository.save(teacher);
    }
    
    @Override
    public Optional<Teacher> updateTeacher(Integer id, Teacher teacherDetails) {
        return teacherRepository.findById(id).map(existingTeacher -> {
            // 更新教师信息，但保留ID和创建时间
            if (teacherDetails.getUserId() != null) {
                existingTeacher.setUserId(teacherDetails.getUserId());
            }
            if (teacherDetails.getName() != null) {
                existingTeacher.setName(teacherDetails.getName());
            }
            if (teacherDetails.getGender() != null) {
                existingTeacher.setGender(teacherDetails.getGender());
            }
            if (teacherDetails.getAge() != null) {
                existingTeacher.setAge(teacherDetails.getAge());
            }
            if (teacherDetails.getTeacherNo() != null) {
                existingTeacher.setTeacherNo(teacherDetails.getTeacherNo());
            }
            if (teacherDetails.getTitle() != null) {
                existingTeacher.setTitle(teacherDetails.getTitle());
            }
            if (teacherDetails.getDepartment() != null) {
                existingTeacher.setDepartment(teacherDetails.getDepartment());
            }
            if (teacherDetails.getPhone() != null) {
                existingTeacher.setPhone(teacherDetails.getPhone());
            }
            if (teacherDetails.getEmail() != null) {
                existingTeacher.setEmail(teacherDetails.getEmail());
            }
            // 保存更新后的教师信息
            return teacherRepository.save(existingTeacher);
        });
    }
    
    @Override
    @Transactional
    public boolean deleteTeacher(Integer id) {
        return teacherRepository.findById(id).map(teacher -> {
            // 可以选择是否同时删除关联的用户账号
            // 这里暂不删除用户账号，只删除教师信息
            teacherRepository.delete(teacher);
            return true;
        }).orElse(false);
    }
} 