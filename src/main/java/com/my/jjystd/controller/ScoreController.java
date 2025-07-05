package com.my.jjystd.controller;

import com.my.jjystd.common.Result;
import com.my.jjystd.entity.Score;
import com.my.jjystd.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "成绩管理", description = "成绩信息管理API")
@RestController
@RequestMapping("/api/score")
public class ScoreController {

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * 根据ID查询成绩信息
     * @param id 成绩ID
     * @return 成绩信息
     */
    @Operation(summary = "根据ID查询成绩", description = "通过成绩ID获取成绩详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Score.class))),
            @ApiResponse(responseCode = "404", description = "成绩不存在")
    })
    @GetMapping("/{id}")
    public Result<Score> getScoreById(
            @Parameter(description = "成绩ID", required = true)
            @PathVariable Integer id) {
        return scoreService.findScoreById(id)
                .map(Result::success)
                .orElse(Result.notFound());
    }
    
    /**
     * 根据学生ID查询成绩
     * @param studentId 学生ID
     * @return 成绩列表
     */
    @Operation(summary = "查询学生的所有成绩", description = "获取指定学生的所有课程成绩")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/student/{studentId}")
    public Result<List<Score>> getScoresByStudentId(
            @Parameter(description = "学生ID", required = true)
            @PathVariable Integer studentId) {
        List<Score> scores = scoreService.findScoresByStudentId(studentId);
        return Result.success(scores);
    }
    
    /**
     * 分页根据学生ID查询成绩
     * @param studentId 学生ID
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    @Operation(summary = "分页查询学生的所有成绩", description = "分页获取指定学生的所有课程成绩")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/student/{studentId}/page")
    public Result<Page<Score>> getPagedScoresByStudentId(
            @Parameter(description = "学生ID", required = true)
            @PathVariable Integer studentId,
            Pageable pageable) {
        Page<Score> scores = scoreService.findScoresByStudentId(studentId, pageable);
        return Result.success(scores);
    }
    
    /**
     * 根据课程ID查询成绩
     * @param courseId 课程ID
     * @return 成绩列表
     */
    @Operation(summary = "查询课程的所有成绩", description = "获取指定课程的所有学生成绩")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/course/{courseId}")
    public Result<List<Score>> getScoresByCourseId(
            @Parameter(description = "课程ID", required = true)
            @PathVariable Integer courseId) {
        List<Score> scores = scoreService.findScoresByCourseId(courseId);
        return Result.success(scores);
    }
    
    /**
     * 分页根据课程ID查询成绩
     * @param courseId 课程ID
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    @Operation(summary = "分页查询课程的所有成绩", description = "分页获取指定课程的所有学生成绩")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/course/{courseId}/page")
    public Result<Page<Score>> getPagedScoresByCourseId(
            @Parameter(description = "课程ID", required = true)
            @PathVariable Integer courseId,
            Pageable pageable) {
        Page<Score> scores = scoreService.findScoresByCourseId(courseId, pageable);
        return Result.success(scores);
    }
    
    /**
     * 根据教师ID查询成绩
     * @param teacherId 教师ID
     * @return 成绩列表
     */
    @Operation(summary = "查询教师的所有课程成绩", description = "获取指定教师教授的所有课程的学生成绩")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/teacher/{teacherId}")
    public Result<List<Score>> getScoresByTeacherId(
            @Parameter(description = "教师ID", required = true)
            @PathVariable Integer teacherId) {
        List<Score> scores = scoreService.findScoresByTeacherId(teacherId);
        return Result.success(scores);
    }
    
    /**
     * 分页根据教师ID查询成绩
     * @param teacherId 教师ID
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    @Operation(summary = "分页查询教师的所有课程成绩", description = "分页获取指定教师教授的所有课程的学生成绩")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/teacher/{teacherId}/page")
    public Result<Page<Score>> getPagedScoresByTeacherId(
            @Parameter(description = "教师ID", required = true)
            @PathVariable Integer teacherId,
            Pageable pageable) {
        Page<Score> scores = scoreService.findScoresByTeacherId(teacherId, pageable);
        return Result.success(scores);
    }
    
    /**
     * 获取所有成绩列表
     * @return 成绩列表
     */
    @Operation(summary = "获取所有成绩", description = "获取系统中所有成绩的列表")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/list")
    public Result<List<Score>> getAllScores() {
        List<Score> scores = scoreService.findAllScores();
        return Result.success(scores);
    }
    
    /**
     * 分页获取所有成绩列表
     * @param pageable 分页参数
     * @return 分页成绩列表
     */
    @Operation(summary = "分页获取所有成绩", description = "分页获取系统中所有成绩的列表")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/page")
    public Result<Page<Score>> getPagedScores(Pageable pageable) {
        Page<Score> scores = scoreService.findAllScores(pageable);
        return Result.success(scores);
    }
    
    /**
     * 根据学生ID和课程ID查询成绩
     * @param studentId 学生ID
     * @param courseId 课程ID
     * @return 成绩信息
     */
    @Operation(summary = "查询学生特定课程成绩", description = "获取指定学生在特定课程中的成绩")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功"),
            @ApiResponse(responseCode = "404", description = "成绩不存在")
    })
    @GetMapping("/student/{studentId}/course/{courseId}")
    public Result<Score> getScoreByStudentIdAndCourseId(
            @Parameter(description = "学生ID", required = true)
            @PathVariable Integer studentId,
            @Parameter(description = "课程ID", required = true)
            @PathVariable Integer courseId) {
        Score score = scoreService.findScoreByStudentIdAndCourseId(studentId, courseId);
        return score != null ? Result.success(score) : Result.notFound();
    }
    
    /**
     * 根据分数范围查询成绩
     * @param minScore 最低分数
     * @param maxScore 最高分数
     * @return 成绩列表
     */
    @Operation(summary = "根据分数范围查询成绩", description = "获取指定分数范围内的所有成绩")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/range")
    public Result<List<Score>> getScoresByScoreRange(
            @Parameter(description = "最低分数", required = true)
            @RequestParam Float minScore,
            @Parameter(description = "最高分数", required = true)
            @RequestParam Float maxScore) {
        List<Score> scores = scoreService.findScoresByScoreRange(minScore, maxScore);
        return Result.success(scores);
    }
    
    /**
     * 新增成绩
     * @param score 成绩信息
     * @return 保存后的成绩信息
     */
    @Operation(summary = "新增成绩", description = "添加新成绩信息")
    @ApiResponse(responseCode = "200", description = "添加成功")
    @PostMapping
    public Result<Score> createScore(
            @Parameter(description = "成绩信息", required = true)
            @RequestBody Score score) {
        Score savedScore = scoreService.saveScore(score);
        return Result.success(savedScore);
    }
    
    /**
     * 更新成绩信息
     * @param id 成绩ID
     * @param score 更新的成绩信息
     * @return 更新后的成绩信息
     */
    @Operation(summary = "更新成绩", description = "通过ID更新成绩信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "404", description = "成绩不存在")
    })
    @PutMapping("/{id}")
    public Result<Score> updateScore(
            @Parameter(description = "成绩ID", required = true)
            @PathVariable Integer id,
            @Parameter(description = "更新的成绩信息", required = true)
            @RequestBody Score score) {
        return scoreService.updateScore(id, score)
                .map(updatedScore -> Result.success(updatedScore, "更新成绩信息成功"))
                .orElse(Result.notFound());
    }
    
    /**
     * 删除成绩
     * @param id 成绩ID
     * @return 操作结果
     */
    @Operation(summary = "删除成绩", description = "通过ID删除成绩")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "404", description = "成绩不存在")
    })
    @DeleteMapping("/{id}")
    public Result<Void> deleteScore(
            @Parameter(description = "成绩ID", required = true)
            @PathVariable Integer id) {
        boolean isDeleted = scoreService.deleteScore(id);
        return isDeleted ? Result.success(null, "删除成绩成功") : Result.notFound();
    }
} 