# 学生成绩管理系统

## 系统概述

学生成绩管理系统是一个基于Spring Boot的Web应用，提供学生、教师和管理员三种角色的用户管理，以及课程和成绩的管理功能。系统采用RESTful API设计，前后端分离架构。

## 系统登录说明

系统支持三种类型的用户登录：

### 1. 学生登录
- 使用学号(student_no)作为用户名
- 默认密码为"123456"
- 创建学生信息时，系统会自动创建对应的用户账号

### 2. 教师登录
- 使用教师编号(teacher_no)作为用户名
- 默认密码为"123456"
- 创建教师信息时，系统会自动创建对应的用户账号

### 3. 管理员登录
- 系统启动时自动创建超级管理员账号
- 用户名：admin
- 默认密码：admin123
- 建议首次登录后修改密码

## 后端技术栈

- 核心框架：Spring Boot
- 持久层：Spring Data JPA
- 数据库：MySQL
- API文档：Swagger/OpenAPI 3.0
- 其他：Lombok, Spring Web

## API接口文档

### 用户管理API

#### 登录接口

```
POST /api/user/login
Content-Type: application/json

{
  "username": "用户名/学号/教师编号",
  "password": "密码"
}
```

登录成功返回：
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "id": 1,
    "username": "用户名/学号/教师编号",
    "role": "student/teacher/admin",
    "phone": "手机号",
    "email": "邮箱",
    "active": true,
    "profileId": 1,        // 关联的学生/教师ID（管理员没有）
    "realName": "真实姓名"  // 学生/教师真实姓名（管理员没有）
  }
}
```

登录失败返回：
```json
{
  "code": 401,
  "message": "用户名或密码错误",
  "data": null
}
```

#### 获取用户信息

```
GET /api/user/{id}
```

#### 根据用户名查询用户

```
GET /api/user/username/{username}
```

#### 获取所有用户

```
GET /api/user/list
```

#### 新增用户

```
POST /api/user
Content-Type: application/json

{
  "username": "用户名",
  "password": "密码",
  "role": "角色(student/teacher/admin)",
  "phone": "手机号",
  "email": "邮箱",
  "active": true
}
```

#### 更新用户

```
PUT /api/user/{id}
Content-Type: application/json

{
  "username": "用户名",
  "password": "密码",
  "role": "角色",
  "phone": "手机号",
  "email": "邮箱",
  "active": true
}
```

#### 删除用户

```
DELETE /api/user/{id}
```

### 学生管理API

#### 获取学生信息

```
GET /api/student/{id}
```

#### 获取所有学生

```
GET /api/student/list
```

#### 新增学生

```
POST /api/student
Content-Type: application/json

{
  "name": "姓名",
  "gender": "性别",
  "age": 20,
  "studentNo": "学号",
  "className": "班级",
  "phone": "手机号",
  "email": "邮箱",
  "address": "地址",
  "department": "院系",
  "major": "专业"
}
```

#### 更新学生信息

```
PUT /api/student/{id}
Content-Type: application/json

{
  "name": "姓名",
  "gender": "性别",
  "age": 20,
  "studentNo": "学号",
  "className": "班级",
  "phone": "手机号",
  "email": "邮箱",
  "address": "地址",
  "department": "院系",
  "major": "专业"
}
```

#### 删除学生

```
DELETE /api/student/{id}
```

### 教师管理API

#### 获取教师信息

```
GET /api/teacher/{id}
```

#### 获取所有教师

```
GET /api/teacher/list
```

#### 新增教师

```
POST /api/teacher
Content-Type: application/json

{
  "name": "姓名",
  "gender": "性别",
  "age": 35,
  "teacherNo": "教师编号",
  "title": "职称",
  "department": "院系",
  "phone": "手机号",
  "email": "邮箱"
}
```

#### 更新教师信息

```
PUT /api/teacher/{id}
Content-Type: application/json

{
  "name": "姓名",
  "gender": "性别",
  "age": 35,
  "teacherNo": "教师编号",
  "title": "职称",
  "department": "院系",
  "phone": "手机号",
  "email": "邮箱"
}
```

#### 删除教师

```
DELETE /api/teacher/{id}
```

### 课程管理API

#### 获取课程信息

```
GET /api/course/{id}
```

#### 根据名称搜索课程

```
GET /api/course/search?name=课程名称
```

#### 根据课程代码查询课程

```
GET /api/course/code/{code}
```

#### 查询教师的课程

```
GET /api/course/teacher/{teacherId}
```

#### 获取所有课程

```
GET /api/course/list
```

#### 新增课程

```
POST /api/course
Content-Type: application/json

{
  "name": "课程名称",
  "courseCode": "课程代码",
  "description": "课程描述",
  "teacher": {
    "id": 1
  },
  "credit": 3.0,
  "status": 1
}
```

#### 更新课程

```
PUT /api/course/{id}
Content-Type: application/json

{
  "name": "课程名称",
  "courseCode": "课程代码",
  "description": "课程描述",
  "teacher": {
    "id": 1
  },
  "credit": 3.0,
  "status": 1
}
```

#### 删除课程

```
DELETE /api/course/{id}
```

### 成绩管理API

#### 获取成绩信息

```
GET /api/score/{id}
```

#### 查询学生的所有成绩

```
GET /api/score/student/{studentId}
```

#### 查询课程的所有成绩

```
GET /api/score/course/{courseId}
```

#### 查询教师评定的所有成绩

```
GET /api/score/teacher/{teacherId}
```

#### 查询学生特定课程成绩

```
GET /api/score/student/{studentId}/course/{courseId}
```

#### 根据分数范围查询成绩

```
GET /api/score/range?minScore=60&maxScore=100
```

#### 获取所有成绩

```
GET /api/score/list
```

#### 新增成绩

```
POST /api/score
Content-Type: application/json

{
  "studentId": 1,
  "course": {
    "id": 1
  },
  "teacher": {
    "id": 1
  },
  "score": 85.5,
  "remark": "备注信息"
}
```

#### 更新成绩

```
PUT /api/score/{id}
Content-Type: application/json

{
  "studentId": 1,
  "course": {
    "id": 1
  },
  "teacher": {
    "id": 1
  },
  "score": 85.5,
  "remark": "备注信息"
}
```

#### 删除成绩

```
DELETE /api/score/{id}
```

## 测试用例

### 学生登录测试
```
POST /api/user/login
Content-Type: application/json

{
  "username": "2023001",
  "password": "123456"
}
```

### 教师登录测试
```
POST /api/user/login
Content-Type: application/json

{
  "username": "T2024001",
  "password": "123456"
}
```

### 管理员登录测试
```
POST /api/user/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

## 系统数据模型

### User (用户)
- id: 主键
- username: 用户名
- password: 密码
- role: 角色(student/teacher/admin)
- phone: 手机号
- email: 邮箱
- active: 是否激活
- createTime: 创建时间

### Student (学生)
- id: 主键
- userId: 关联用户ID
- name: 姓名
- gender: 性别
- age: 年龄
- studentNo: 学号
- className: 班级
- phone: 手机号
- email: 邮箱
- address: 地址
- department: 院系
- major: 专业
- createTime: 创建时间

### Teacher (教师)
- id: 主键
- userId: 关联用户ID
- name: 姓名
- gender: 性别
- age: 年龄
- teacherNo: 教师编号
- title: 职称
- department: 院系
- phone: 手机号
- email: 邮箱
- createTime: 创建时间

### Course (课程)
- id: 主键
- name: 课程名称
- courseCode: 课程代码
- description: 课程描述
- teacher: 关联教师
- credit: 学分
- status: 状态(1启用，0禁用)
- createTime: 创建时间

### Score (成绩)
- id: 主键
- studentId: 关联学生ID
- course: 关联课程
- teacher: 关联教师
- score: 分数
- remark: 备注
- createTime: 创建时间 