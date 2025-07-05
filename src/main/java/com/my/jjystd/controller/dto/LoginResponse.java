package com.my.jjystd.controller.dto;

import com.my.jjystd.entity.Student;
import com.my.jjystd.entity.Teacher;
import com.my.jjystd.entity.User;
import lombok.Data;

/**
 * 登录响应DTO
 */
@Data
public class LoginResponse {
    /**
     * 用户ID
     */
    private Integer id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 用户角色
     */
    private String role;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 是否激活
     */
    private Boolean active;
    
    /**
     * 关联的学生/教师ID
     */
    private Integer profileId;
    
    /**
     * 真实姓名（学生/教师姓名）
     */
    private String realName;
    
    /**
     * 从User实体创建LoginResponse
     * @param user 用户实体
     * @return LoginResponse对象
     */
    public static LoginResponse fromUser(User user) {
        LoginResponse response = new LoginResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setPhone(user.getPhone());
        response.setEmail(user.getEmail());
        response.setActive(user.getActive());
        return response;
    }
    
    /**
     * 从User和Student实体创建LoginResponse
     * @param user 用户实体
     * @param student 学生实体
     * @return LoginResponse对象
     */
    public static LoginResponse fromUserAndStudent(User user, Student student) {
        LoginResponse response = fromUser(user);
        if (student != null) {
            response.setProfileId(student.getId());
            response.setRealName(student.getName());
        }
        return response;
    }
    
    /**
     * 从User和Teacher实体创建LoginResponse
     * @param user 用户实体
     * @param teacher 教师实体
     * @return LoginResponse对象
     */
    public static LoginResponse fromUserAndTeacher(User user, Teacher teacher) {
        LoginResponse response = fromUser(user);
        if (teacher != null) {
            response.setProfileId(teacher.getId());
            response.setRealName(teacher.getName());
        }
        return response;
    }
} 