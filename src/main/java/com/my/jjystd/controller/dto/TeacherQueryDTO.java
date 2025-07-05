package com.my.jjystd.controller.dto;

import lombok.Data;

/**
 * 教师查询条件DTO
 */
@Data
public class TeacherQueryDTO {
    private String name;        // 姓名
    private String teacherNo;   // 教师编号
    private String gender;      // 性别
    private String title;       // 职称
    private String department;  // 院系
    private String phone;       // 电话
    private String email;       // 邮箱
} 