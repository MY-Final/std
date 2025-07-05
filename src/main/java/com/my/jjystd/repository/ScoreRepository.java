package com.my.jjystd.repository;

import com.my.jjystd.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 分页根据学生ID查询成绩
     * @param studentId 学生ID
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    Page<Score> findByStudentId(Integer studentId, Pageable pageable);
    
    /**
     * 根据课程ID查询成绩
     * @param courseId 课程ID
     * @return 成绩列表
     */
    List<Score> findByCourseId(Integer courseId);
    
    /**
     * 分页根据课程ID查询成绩
     * @param courseId 课程ID
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    Page<Score> findByCourseId(Integer courseId, Pageable pageable);
    
    /**
     * 根据教师ID查询成绩
     * @param teacherId 教师ID
     * @return 成绩列表
     */
    List<Score> findByTeacherId(Integer teacherId);
    
    /**
     * 分页根据教师ID查询成绩
     * @param teacherId 教师ID
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    Page<Score> findByTeacherId(Integer teacherId, Pageable pageable);
    
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
    
    /**
     * 分页根据分数范围查询成绩
     * @param minScore 最低分数
     * @param maxScore 最高分数
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    Page<Score> findByScoreBetween(Float minScore, Float maxScore, Pageable pageable);
} 