package com.my.jjystd.controller.dto;

import lombok.Data;

/**
 * 学生查询条件DTO
 */
@Data
public class StudentQueryDTO {
    private String name;        // 姓名
    private String studentNo;   // 学号
    private String gender;      // 性别
    private String className;   // 班级
    private String department;  // 院系
    private String major;       // 专业
    private String phone;       // 电话
    private String email;       // 邮箱
} 