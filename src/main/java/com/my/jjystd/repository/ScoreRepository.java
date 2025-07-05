package com.my.jjystd.repository;

import com.my.jjystd.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    /**
     * 根据学生ID查询成绩
     * @param studentId 学生ID
     * @return 成绩列表
     */
    List<Score> findByStudentId(Integer studentId);
    
    /**
     * 根据课程ID查询成绩
     * @param courseId 课程ID
     * @return 成绩列表
     */
    List<Score> findByCourseId(Integer courseId);
    
    /**
     * 根据教师ID查询成绩
     * @param teacherId 教师ID
     * @return 成绩列表
     */
    List<Score> findByTeacherId(Integer teacherId);
    
    /**
     * 根据学生ID和课程ID查询成绩
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 成绩
     */
    Score findByStudentIdAndCourseId(Integer studentId, Integer courseId);
    
    /**
     * 根据分数范围查询成绩
     * @param minScore 最低分数
     * @param maxScore 最高分数
     * @return 成绩列表
     */
    List<Score> findByScoreBetween(Float minScore, Float maxScore);
} 