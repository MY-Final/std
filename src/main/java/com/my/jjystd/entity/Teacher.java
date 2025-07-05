package com.my.jjystd.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String name;
    private String gender;
    private Integer age;
    @Column(name = "teacher_no")
    private String teacherNo;
    private String title;
    private String department;
    private String phone;
    private String email;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}