package com.my.jjystd.service.impl;

import com.my.jjystd.entity.Score;
import com.my.jjystd.repository.ScoreRepository;
import com.my.jjystd.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class ScoreServiceImpl implements ScoreService {
    
    private final ScoreRepository scoreRepository;
    
    @Autowired
    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }
    
    @Override
    public Optional<Score> findScoreById(Integer id) {
        return scoreRepository.findById(id);
    }
    
    @Override
    public List<Score> findScoresByStudentId(Integer studentId) {
        return scoreRepository.findByStudentId(studentId);
    }
    
    @Override
    public List<Score> findScoresByCourseId(Integer courseId) {
        return scoreRepository.findByCourseId(courseId);
    }
    
    @Override
    public List<Score> findScoresByTeacherId(Integer teacherId) {
        return scoreRepository.findByTeacherId(teacherId);
    }
    
    @Override
    public Score findScoreByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        return scoreRepository.findByStudentIdAndCourseId(studentId, courseId);
    }
    
    @Override
    public List<Score> findScoresByScoreRange(Float minScore, Float maxScore) {
        return scoreRepository.findByScoreBetween(minScore, maxScore);
    }
    
    @Override
    public List<Score> findAllScores() {
        return scoreRepository.findAll();
    }
    
    @Override
    public Score saveScore(Score score) {
        // 设置创建时间
        if (score.getCreateTime() == null) {
            score.setCreateTime(new Date());
        }
        return scoreRepository.save(score);
    }
    
    @Override
    public Optional<Score> updateScore(Integer id, Score scoreDetails) {
        return scoreRepository.findById(id).map(existingScore -> {
            // 更新成绩信息，但保留ID和创建时间
            if (scoreDetails.getStudentId() != null) {
                existingScore.setStudentId(scoreDetails.getStudentId());
            }
            if (scoreDetails.getCourse() != null) {
                existingScore.setCourse(scoreDetails.getCourse());
            }
            if (scoreDetails.getTeacher() != null) {
                existingScore.setTeacher(scoreDetails.getTeacher());
            }
            if (scoreDetails.getScore() != null) {
                existingScore.setScore(scoreDetails.getScore());
            }
            if (scoreDetails.getRemark() != null) {
                existingScore.setRemark(scoreDetails.getRemark());
            }
            // 保存更新后的成绩信息
            return scoreRepository.save(existingScore);
        });
    }
    
    @Override
    public boolean deleteScore(Integer id) {
        return scoreRepository.findById(id).map(score -> {
            scoreRepository.delete(score);
            return true;
        }).orElse(false);
    }
} 