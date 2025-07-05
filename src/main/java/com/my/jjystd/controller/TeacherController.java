package com.my.jjystd.controller;

import com.my.jjystd.common.Result;
import com.my.jjystd.entity.Teacher;
import com.my.jjystd.service.TeacherService;
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

@Tag(name = "教师管理", description = "教师信息管理API")
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 根据ID查询教师信息
     * @param id 教师ID
     * @return 教师信息
     */
    @Operation(summary = "根据ID查询教师", description = "通过教师ID获取教师详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Teacher.class))),
            @ApiResponse(responseCode = "404", description = "教师不存在")
    })
    @GetMapping("/{id}")
    public Result<Teacher> getTeacherById(
            @Parameter(description = "教师ID", required = true)
            @PathVariable Integer id) {
        return teacherService.findTeacherById(id)
                .map(Result::success)
                .orElse(Result.notFound());
    }
    
    /**
     * 获取所有教师列表
     * @return 教师列表
     */
    @Operation(summary = "获取所有教师", description = "获取系统中所有教师的列表")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/list")
    public Result<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.findAllTeachers();
        return Result.success(teachers);
    }
    
    /**
     * 分页获取所有教师列表
     * @param pageable 分页参数
     * @return 分页教师列表
     */
    @Operation(summary = "分页获取所有教师", description = "分页获取系统中所有教师的列表")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/page")
    public Result<Page<Teacher>> getPagedTeachers(Pageable pageable) {
        Page<Teacher> teachers = teacherService.findAllTeachers(pageable);
        return Result.success(teachers);
    }
    
    /**
     * 新增教师信息
     * @param teacher 教师信息
     * @return 保存后的教师信息
     */
    @Operation(summary = "新增教师", description = "添加新教师信息")
    @ApiResponse(responseCode = "200", description = "添加成功")
    @PostMapping
    public Result<Teacher> createTeacher(
            @Parameter(description = "教师信息", required = true)
            @RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.saveTeacher(teacher);
        return Result.success(savedTeacher);
    }
    
    /**
     * 更新教师信息
     * @param id 教师ID
     * @param teacher 更新的教师信息
     * @return 更新后的教师信息
     */
    @Operation(summary = "更新教师", description = "通过ID更新教师信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "404", description = "教师不存在")
    })
    @PutMapping("/{id}")
    public Result<Teacher> updateTeacher(
            @Parameter(description = "教师ID", required = true)
            @PathVariable Integer id,
            @Parameter(description = "更新的教师信息", required = true)
            @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(id, teacher)
                .map(updatedTeacher -> Result.success(updatedTeacher, "更新教师信息成功"))
                .orElse(Result.notFound());
    }
    
    /**
     * 删除教师信息
     * @param id 教师ID
     * @return 操作结果
     */
    @Operation(summary = "删除教师", description = "通过ID删除教师")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "404", description = "教师不存在")
    })
    @DeleteMapping("/{id}")
    public Result<Void> deleteTeacher(
            @Parameter(description = "教师ID", required = true)
            @PathVariable Integer id) {
        boolean isDeleted = teacherService.deleteTeacher(id);
        return isDeleted ? Result.success(null, "删除教师成功") : Result.notFound();
    }
} 