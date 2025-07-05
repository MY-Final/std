package com.my.jjystd.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String name;
    private String gender;
    private Integer age;
    private String studentNo;
    private String className;
    private String phone;
    private String email;
    private String address;
    private String department;
    private String major;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}