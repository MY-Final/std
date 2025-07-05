package com.my.jjystd.controller;

import com.my.jjystd.common.Result;
import com.my.jjystd.controller.dto.LoginRequest;
import com.my.jjystd.controller.dto.LoginResponse;
import com.my.jjystd.entity.Student;
import com.my.jjystd.entity.Teacher;
import com.my.jjystd.entity.User;
import com.my.jjystd.service.StudentService;
import com.my.jjystd.service.TeacherService;
import com.my.jjystd.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "用户管理", description = "用户信息管理API")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public UserController(UserService userService, StudentService studentService, TeacherService teacherService) {
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    @Operation(summary = "用户登录", description = "通过用户名/学号/教师编号和密码验证用户身份")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "401", description = "用户名或密码错误")
    })
    @PostMapping("/login")
    public Result<LoginResponse> login(
            @Parameter(description = "登录信息", required = true)
            @RequestBody LoginRequest loginRequest) {
        
        // 首先尝试使用用户名和密码登录
        Optional<User> userOpt = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        
        // 如果登录成功，根据角色进行不同处理
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            LoginResponse loginResponse;
            
            // 根据角色获取额外信息
            switch (user.getRole()) {
                case "student":
                    // 查找关联的学生信息
                    Optional<Student> studentOpt = studentService.findStudentByUserId(user.getId());
                    loginResponse = studentOpt.isPresent() 
                        ? LoginResponse.fromUserAndStudent(user, studentOpt.get())
                        : LoginResponse.fromUser(user);
                    break;
                case "teacher":
                    // 查找关联的教师信息
                    Optional<Teacher> teacherOpt = teacherService.findTeacherByUserId(user.getId());
                    loginResponse = teacherOpt.isPresent()
                        ? LoginResponse.fromUserAndTeacher(user, teacherOpt.get())
                        : LoginResponse.fromUser(user);
                    break;
                case "admin":
                    // 管理员直接返回用户信息
                    loginResponse = LoginResponse.fromUser(user);
                    break;
                default:
                    loginResponse = LoginResponse.fromUser(user);
            }
            
            return Result.success(loginResponse, "登录成功");
        }
        
        // 如果用户名登录失败，尝试使用学号登录
        Optional<Student> studentOpt = studentService.findStudentByStudentNo(loginRequest.getUsername());
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            // 通过学生关联的userId查找用户
            if (student.getUserId() != null) {
                userOpt = userService.findUserById(student.getUserId())
                        .filter(user -> 
                            // 验证密码是否匹配
                            user.getPassword().equals(loginRequest.getPassword()) &&
                            // 验证用户是否激活
                            user.getActive() != null && user.getActive()
                        );
                
                if (userOpt.isPresent()) {
                    // 登录成功，返回用户信息和学生信息
                    LoginResponse loginResponse = LoginResponse.fromUserAndStudent(userOpt.get(), student);
                    return Result.success(loginResponse, "登录成功");
                }
            }
        }
        
        // 如果学号登录失败，尝试使用教师编号登录
        Optional<Teacher> teacherOpt = teacherService.findTeacherByTeacherNo(loginRequest.getUsername());
        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            // 通过教师关联的userId查找用户
            if (teacher.getUserId() != null) {
                userOpt = userService.findUserById(teacher.getUserId())
                        .filter(user -> 
                            // 验证密码是否匹配
                            user.getPassword().equals(loginRequest.getPassword()) &&
                            // 验证用户是否激活
                            user.getActive() != null && user.getActive()
                        );
                
                if (userOpt.isPresent()) {
                    // 登录成功，返回用户信息和教师信息
                    LoginResponse loginResponse = LoginResponse.fromUserAndTeacher(userOpt.get(), teacher);
                    return Result.success(loginResponse, "登录成功");
                }
            }
        }
        
        // 所有登录方式都失败
        return Result.unauthorized("用户名或密码错误");
    }
    
    /**
     * 根据ID查询用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    @Operation(summary = "根据ID查询用户", description = "通过用户ID获取用户详细信息（不包含密码）")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功", 
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    @GetMapping("/{id}")
    public Result<User> getUserById(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Integer id) {
        return userService.findUserById(id)
                .map(user -> {
                    // 出于安全考虑，返回前清除密码
                    user.setPassword(null);
                    return Result.success(user);
                })
                .orElse(Result.notFound());
    }
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    @Operation(summary = "根据用户名查询用户", description = "通过用户名获取用户详细信息（不包含密码）")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功"),
            @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    @GetMapping("/username/{username}")
    public Result<User> getUserByUsername(
            @Parameter(description = "用户名", required = true)
            @PathVariable String username) {
        return userService.findUserByUsername(username)
                .map(user -> {
                    // 出于安全考虑，返回前清除密码
                    user.setPassword(null);
                    return Result.success(user);
                })
                .orElse(Result.notFound());
    }
    
    /**
     * 获取所有用户列表
     * @return 用户列表
     */
    @Operation(summary = "获取所有用户", description = "获取系统中所有用户的列表（不包含密码）")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @GetMapping("/list")
    public Result<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        // 出于安全考虑，返回前清除所有用户的密码
        users.forEach(user -> user.setPassword(null));
        return Result.success(users);
    }
    
    /**
     * 新增用户
     * @param user 用户信息
     * @return 保存后的用户信息
     */
    @Operation(summary = "新增用户", description = "添加新用户信息")
    @ApiResponse(responseCode = "200", description = "添加成功")
    @PostMapping
    public Result<User> createUser(
            @Parameter(description = "用户信息", required = true)
            @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        // 出于安全考虑，返回前清除密码
        savedUser.setPassword(null);
        return Result.success(savedUser);
    }
    
    /**
     * 更新用户信息
     * @param id 用户ID
     * @param user 更新的用户信息
     * @return 更新后的用户信息
     */
    @Operation(summary = "更新用户", description = "通过ID更新用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    @PutMapping("/{id}")
    public Result<User> updateUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Integer id,
            @Parameter(description = "更新的用户信息", required = true)
            @RequestBody User user) {
        return userService.updateUser(id, user)
                .map(updatedUser -> {
                    // 出于安全考虑，返回前清除密码
                    updatedUser.setPassword(null);
                    return Result.success(updatedUser, "更新用户信息成功");
                })
                .orElse(Result.notFound());
    }
    
    /**
     * 删除用户
     * @param id 用户ID
     * @return 操作结果
     */
    @Operation(summary = "删除用户", description = "通过ID删除用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    @DeleteMapping("/{id}")
    public Result<Void> deleteUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Integer id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? Result.success(null, "删除用户成功") : Result.notFound();
    }
} 