package com.my.jjystd.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId; // 关联学生表ID（假设学生表实体为Student）

    @ManyToOne(fetch = FetchType.EAGER)  // 改为立即加载
    @JoinColumn(name = "course_id")
    private Course course; // 关联课程表

    @ManyToOne(fetch = FetchType.EAGER)  // 改为立即加载
    @JoinColumn(name = "teacher_id")
    private Teacher teacher; // 关联操作教师表

    private Float score;

    private String remark;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}
