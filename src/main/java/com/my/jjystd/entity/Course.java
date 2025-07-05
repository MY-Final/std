package com.my.jjystd.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "course_code")
    private String courseCode;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)  // 改为立即加载
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private Float credit;

    private Integer status; // 1启用，0禁用

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}