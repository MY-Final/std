package com.my.jjystd.controller;

import com.my.jjystd.common.Result;
import com.my.jjystd.entity.Course;
import com.my.jjystd.service.CourseService;
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

@Tag(name = "课程管理", description = "课程信息管理API")
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 根据ID查询课程信息
     * @param id 课程ID
     * @return 课程信息
     */
    @Operation(summary = "根据ID查询课程", description = "通过课程ID获取课程详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Course.class))),
            @ApiResponse(responseCode = "404", description = "课程不存在")
    })
    @GetMapping("/{id}")
    public Result<Course> getCourseById(
            @Parameter(description = "课程ID", required = true)
            @PathVariable Integer id) {
        return courseService.findCourseById(id)
                .map(Result::success)
                .orElse(Result.notFound());
    }
    
    /**
     * 根据课程名称查询课程
     * @param name 课程名称
     * @return 课程列表
     */
    @Operation(summary = "根据名称搜索课程", description = "通过课程名称搜索相关课程")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/search")
    public Result<List<Course>> searchCoursesByName(
            @Parameter(description = "课程名称", required = true)
            @RequestParam String name) {
        List<Course> courses = courseService.findCoursesByName(name);
        return Result.success(courses);
    }
    
    /**
     * 分页根据课程名称查询课程
     * @param name 课程名称
     * @param pageable 分页参数
     * @return 分页课程列表
     */
    @Operation(summary = "分页根据名称搜索课程", description = "分页通过课程名称搜索相关课程")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/search/page")
    public Result<Page<Course>> searchPagedCoursesByName(
            @Parameter(description = "课程名称", required = true)
            @RequestParam String name,
            Pageable pageable) {
        Page<Course> courses = courseService.findCoursesByName(name, pageable);
        return Result.success(courses);
    }
    
    /**
     * 根据课程代码查询课程
     * @param code 课程代码
     * @return 课程信息
     */
    @Operation(summary = "根据课程代码查询课程", description = "通过课程代码获取课程详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功"),
            @ApiResponse(responseCode = "404", description = "课程不存在")
    })
    @GetMapping("/code/{code}")
    public Result<Course> getCourseByCode(
            @Parameter(description = "课程代码", required = true)
            @PathVariable String code) {
        Course course = courseService.findByCourseCode(code);
        return course != null ? Result.success(course) : Result.notFound();
    }
    
    /**
     * 根据教师ID查询课程
     * @param teacherId 教师ID
     * @return 课程列表
     */
    @Operation(summary = "查询教师的课程", description = "获取指定教师教授的所有课程")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/teacher/{teacherId}")
    public Result<List<Course>> getCoursesByTeacherId(
            @Parameter(description = "教师ID", required = true)
            @PathVariable Integer teacherId) {
        List<Course> courses = courseService.findCoursesByTeacherId(teacherId);
        return Result.success(courses);
    }
    
    /**
     * 分页根据教师ID查询课程
     * @param teacherId 教师ID
     * @param pageable 分页参数
     * @return 分页课程列表
     */
    @Operation(summary = "分页查询教师的课程", description = "分页获取指定教师教授的所有课程")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/teacher/{teacherId}/page")
    public Result<Page<Course>> getPagedCoursesByTeacherId(
            @Parameter(description = "教师ID", required = true)
            @PathVariable Integer teacherId,
            Pageable pageable) {
        Page<Course> courses = courseService.findCoursesByTeacherId(teacherId, pageable);
        return Result.success(courses);
    }
    
    /**
     * 获取所有课程
     * @return 课程列表
     */
    @Operation(summary = "获取所有课程", description = "获取系统中所有课程的列表")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/list")
    public Result<List<Course>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();
        return Result.success(courses);
    }
    
    /**
     * 分页获取所有课程
     * @param pageable 分页参数
     * @return 分页课程列表
     */
    @Operation(summary = "分页获取所有课程", description = "分页获取系统中所有课程的列表")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/page")
    public Result<Page<Course>> getPagedCourses(Pageable pageable) {
        Page<Course> courses = courseService.findAllCourses(pageable);
        return Result.success(courses);
    }
    
    /**
     * 新增课程
     * @param course 课程信息
     * @return 保存后的课程信息
     */
    @Operation(summary = "新增课程", description = "添加新课程信息")
    @ApiResponse(responseCode = "200", description = "添加成功")
    @PostMapping
    public Result<Course> createCourse(
            @Parameter(description = "课程信息", required = true)
            @RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return Result.success(savedCourse);
    }
    
    /**
     * 更新课程信息
     * @param id 课程ID
     * @param course 更新的课程信息
     * @return 更新后的课程信息
     */
    @Operation(summary = "更新课程", description = "通过ID更新课程信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "404", description = "课程不存在")
    })
    @PutMapping("/{id}")
    public Result<Course> updateCourse(
            @Parameter(description = "课程ID", required = true)
            @PathVariable Integer id,
            @Parameter(description = "更新的课程信息", required = true)
            @RequestBody Course course) {
        return courseService.updateCourse(id, course)
                .map(updatedCourse -> Result.success(updatedCourse, "更新课程信息成功"))
                .orElse(Result.notFound());
    }
    
    /**
     * 删除课程
     * @param id 课程ID
     * @return 操作结果
     */
    @Operation(summary = "删除课程", description = "通过ID删除课程")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "404", description = "课程不存在")
    })
    @DeleteMapping("/{id}")
    public Result<Void> deleteCourse(
            @Parameter(description = "课程ID", required = true)
            @PathVariable Integer id) {
        boolean isDeleted = courseService.deleteCourse(id);
        return isDeleted ? Result.success(null, "删除课程成功") : Result.notFound();
    }
} 