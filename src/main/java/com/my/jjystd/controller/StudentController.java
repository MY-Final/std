package com.my.jjystd.controller;

import com.my.jjystd.common.Result;
import com.my.jjystd.entity.Student;
import com.my.jjystd.service.StudentService;
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

@Tag(name = "学生管理", description = "学生信息管理API")
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 根据ID查询学生信息
     * @param id 学生ID
     * @return 学生信息
     */
    @Operation(summary = "根据ID查询学生", description = "通过学生ID获取学生详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "学生不存在")
    })
    @GetMapping("/{id}")
    public Result<Student> getStudentById(
            @Parameter(description = "学生ID", required = true)
            @PathVariable Integer id) {
        return studentService.findStudentById(id)
                .map(Result::success)
                .orElse(Result.notFound());
    }
    
    /**
     * 获取所有学生列表
     * @return 学生列表
     */
    @Operation(summary = "获取所有学生", description = "获取系统中所有学生的列表")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/list")
    public Result<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return Result.success(students);
    }
    
    /**
     * 分页获取所有学生列表
     * @param pageable 分页参数
     * @return 分页学生列表
     */
    @Operation(summary = "分页获取所有学生", description = "分页获取系统中所有学生的列表")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/page")
    public Result<Page<Student>> getPagedStudents(Pageable pageable) {
        Page<Student> students = studentService.findAllStudents(pageable);
        return Result.success(students);
    }
    
    /**
     * 新增学生信息
     * @param student 学生信息
     * @return 保存后的学生信息
     */
    @Operation(summary = "新增学生", description = "添加新学生信息")
    @ApiResponse(responseCode = "200", description = "添加成功")
    @PostMapping
    public Result<Student> createStudent(
            @Parameter(description = "学生信息", required = true)
            @RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return Result.success(savedStudent);
    }
    
    /**
     * 更新学生信息
     * @param id 学生ID
     * @param student 更新的学生信息
     * @return 更新后的学生信息
     */
    @Operation(summary = "更新学生", description = "通过ID更新学生信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "404", description = "学生不存在")
    })
    @PutMapping("/{id}")
    public Result<Student> updateStudent(
            @Parameter(description = "学生ID", required = true)
            @PathVariable Integer id,
            @Parameter(description = "更新的学生信息", required = true)
            @RequestBody Student student) {
        return studentService.updateStudent(id, student)
                .map(updatedStudent -> Result.success(updatedStudent, "更新学生信息成功"))
                .orElse(Result.notFound());
    }
    
    /**
     * 删除学生信息
     * @param id 学生ID
     * @return 操作结果
     */
    @Operation(summary = "删除学生", description = "通过ID删除学生")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "404", description = "学生不存在")
    })
    @DeleteMapping("/{id}")
    public Result<Void> deleteStudent(
            @Parameter(description = "学生ID", required = true)
            @PathVariable Integer id) {
        boolean isDeleted = studentService.deleteStudent(id);
        return isDeleted ? Result.success(null, "删除学生成功") : Result.notFound();
    }
} 