package com.my.jjystd.controller.dto;

import lombok.Data;

/**
 * 课程查询条件DTO
 */
@Data
public class CourseQueryDTO {
    private String name;            // 课程名称
    private String courseCode;      // 课程代码
    private String description;     // 课程描述
    private Integer teacherId;      // 教师ID
    private String teacherName;     // 教师姓名
    private Float credit;           // 学分
    private Integer status;         // 状态
} 