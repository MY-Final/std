package com.my.jjystd.service;

import com.my.jjystd.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ScoreService {
    /**
     * 根据ID查询成绩信息
     * @param id 成绩ID
     * @return 成绩信息
     */
    Optional<Score> findScoreById(Integer id);
    
    /**
     * 根据学生ID查询成绩
     * @param studentId 学生ID
     * @return 成绩列表
     */
    List<Score> findScoresByStudentId(Integer studentId);
    
    /**
     * 分页根据学生ID查询成绩
     * @param studentId 学生ID
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    Page<Score> findScoresByStudentId(Integer studentId, Pageable pageable);
    
    /**
     * 根据课程ID查询成绩
     * @param courseId 课程ID
     * @return 成绩列表
     */
    List<Score> findScoresByCourseId(Integer courseId);
    
    /**
     * 分页根据课程ID查询成绩
     * @param courseId 课程ID
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    Page<Score> findScoresByCourseId(Integer courseId, Pageable pageable);
    
    /**
     * 根据教师ID查询成绩
     * @param teacherId 教师ID
     * @return 成绩列表
     */
    List<Score> findScoresByTeacherId(Integer teacherId);
    
    /**
     * 分页根据教师ID查询成绩
     * @param teacherId 教师ID
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    Page<Score> findScoresByTeacherId(Integer teacherId, Pageable pageable);
    
    /**
     * 根据学生ID和课程ID查询成绩
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 成绩
     */
    Score findScoreByStudentIdAndCourseId(Integer studentId, Integer courseId);
    
    /**
     * 根据分数范围查询成绩
     * @param minScore 最低分数
     * @param maxScore 最高分数
     * @return 成绩列表
     */
    List<Score> findScoresByScoreRange(Float minScore, Float maxScore);
    
    /**
     * 分页根据分数范围查询成绩
     * @param minScore 最低分数
     * @param maxScore 最高分数
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    Page<Score> findScoresByScoreRange(Float minScore, Float maxScore, Pageable pageable);
    
    /**
     * 获取所有成绩
     * @return 成绩列表
     */
    List<Score> findAllScores();
    
    /**
     * 分页获取所有成绩
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    Page<Score> findAllScores(Pageable pageable);
    
    /**
     * 新增成绩
     * @param score 成绩信息
     * @return 保存后的成绩信息
     */
    Score saveScore(Score score);
    
    /**
     * 更新成绩信息
     * @param id 成绩ID
     * @param score 更新的成绩信息
     * @return 更新后的成绩信息
     */
    Optional<Score> updateScore(Integer id, Score score);
    
    /**
     * 删除成绩
     * @param id 成绩ID
     * @return 是否删除成功
     */
    boolean deleteScore(Integer id);
} 