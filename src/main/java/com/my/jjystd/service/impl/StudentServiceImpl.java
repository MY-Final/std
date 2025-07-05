package com.my.jjystd.service.impl;

import com.my.jjystd.controller.dto.StudentQueryDTO;
import com.my.jjystd.entity.Student;
import com.my.jjystd.entity.User;
import com.my.jjystd.repository.StudentRepository;
import com.my.jjystd.service.StudentService;
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
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final UserService userService;
    
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }
    
    @Override
    public Optional<Student> findStudentById(Integer id) {
        return studentRepository.findById(id);
    }
    
    @Override
    public Optional<Student> findStudentByStudentNo(String studentNo) {
        return studentRepository.findByStudentNo(studentNo);
    }
    
    @Override
    public Optional<Student> findStudentByUserId(Integer userId) {
        return studentRepository.findByUserId(userId);
    }
    
    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
    
    @Override
    public Page<Student> findAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
    
    @Override
    public List<Student> findStudentsByCondition(StudentQueryDTO queryDTO) {
        Specification<Student> spec = buildSpecification(queryDTO);
        return studentRepository.findAll(spec);
    }
    
    @Override
    public Page<Student> findStudentsByCondition(StudentQueryDTO queryDTO, Pageable pageable) {
        Specification<Student> spec = buildSpecification(queryDTO);
        return studentRepository.findAll(spec, pageable);
    }
    
    /**
     * 构建动态查询条件
     * @param queryDTO 查询参数
     * @return 查询条件Specification
     */
    private Specification<Student> buildSpecification(StudentQueryDTO queryDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // 根据姓名模糊查询
            if (StringUtils.hasText(queryDTO.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + queryDTO.getName() + "%"));
            }
            
            // 根据学号模糊查询
            if (StringUtils.hasText(queryDTO.getStudentNo())) {
                predicates.add(criteriaBuilder.like(root.get("studentNo"), "%" + queryDTO.getStudentNo() + "%"));
            }
            
            // 根据性别精确查询
            if (StringUtils.hasText(queryDTO.getGender())) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), queryDTO.getGender()));
            }
            
            // 根据班级模糊查询
            if (StringUtils.hasText(queryDTO.getClassName())) {
                predicates.add(criteriaBuilder.like(root.get("className"), "%" + queryDTO.getClassName() + "%"));
            }
            
            // 根据院系模糊查询
            if (StringUtils.hasText(queryDTO.getDepartment())) {
                predicates.add(criteriaBuilder.like(root.get("department"), "%" + queryDTO.getDepartment() + "%"));
            }
            
            // 根据专业模糊查询
            if (StringUtils.hasText(queryDTO.getMajor())) {
                predicates.add(criteriaBuilder.like(root.get("major"), "%" + queryDTO.getMajor() + "%"));
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
    public Student saveStudent(Student student) {
        // 设置创建时间
        if (student.getCreateTime() == null) {
            student.setCreateTime(new Date());
        }
        
        // 自动创建用户账号
        if (student.getUserId() == null && student.getStudentNo() != null) {
            // 创建用户对象
            User user = new User();
            user.setUsername(student.getStudentNo()); // 使用学号作为用户名
            user.setPassword("123456"); // 默认密码
            user.setRole("student"); // 角色为学生
            user.setPhone(student.getPhone());
            user.setEmail(student.getEmail());
            user.setActive(true);
            
            // 保存用户并获取用户ID
            User savedUser = userService.saveUser(user);
            student.setUserId(savedUser.getId());
        }
        
        return studentRepository.save(student);
    }
    
    @Override
    public Optional<Student> updateStudent(Integer id, Student studentDetails) {
        return studentRepository.findById(id).map(existingStudent -> {
            // 更新学生信息，但保留ID和创建时间
            if (studentDetails.getUserId() != null) {
                existingStudent.setUserId(studentDetails.getUserId());
            }
            if (studentDetails.getName() != null) {
                existingStudent.setName(studentDetails.getName());
            }
            if (studentDetails.getGender() != null) {
                existingStudent.setGender(studentDetails.getGender());
            }
            if (studentDetails.getAge() != null) {
                existingStudent.setAge(studentDetails.getAge());
            }
            if (studentDetails.getStudentNo() != null) {
                existingStudent.setStudentNo(studentDetails.getStudentNo());
            }
            if (studentDetails.getClassName() != null) {
                existingStudent.setClassName(studentDetails.getClassName());
            }
            if (studentDetails.getPhone() != null) {
                existingStudent.setPhone(studentDetails.getPhone());
            }
            if (studentDetails.getEmail() != null) {
                existingStudent.setEmail(studentDetails.getEmail());
            }
            if (studentDetails.getAddress() != null) {
                existingStudent.setAddress(studentDetails.getAddress());
            }
            if (studentDetails.getDepartment() != null) {
                existingStudent.setDepartment(studentDetails.getDepartment());
            }
            if (studentDetails.getMajor() != null) {
                existingStudent.setMajor(studentDetails.getMajor());
            }
            // 保存更新后的学生信息
            return studentRepository.save(existingStudent);
        });
    }
    
    @Override
    @Transactional
    public boolean deleteStudent(Integer id) {
        return studentRepository.findById(id).map(student -> {
            // 可以选择是否同时删除关联的用户账号
            // 这里暂不删除用户账号，只删除学生信息
            studentRepository.delete(student);
            return true;
        }).orElse(false);
    }
} 