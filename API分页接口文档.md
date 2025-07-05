# 分页API接口文档

## 概述

本文档描述了系统中所有支持分页的API接口。所有分页接口都支持以下查询参数：

- `page`：页码，从0开始（默认为0）
- `size`：每页大小（默认为20）
- `sort`：排序字段，格式为`字段名,排序方向`，例如`id,desc`表示按id降序排序

## 学生管理API

### 分页获取所有学生

- **URL**: `/api/student/page`
- **方法**: GET
- **描述**: 分页获取系统中所有学生的列表
- **参数**:
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**:
  ```json
  {
    "code": 200,
    "message": "操作成功",
    "data": {
      "content": [
        {
          "id": 1,
          "userId": 1,
          "name": "张三",
          "gender": "男",
          "age": 20,
          "studentNo": "2023001",
          "className": "计算机科学2023级1班",
          "phone": "13800138001",
          "email": "zhangsan@example.com",
          "address": "北京市海淀区",
          "department": "计算机科学系",
          "major": "计算机科学与技术",
          "createTime": "2023-09-01T08:00:00"
        },
        // 更多学生...
      ],
      "pageable": {
        "sort": {
          "sorted": true,
          "unsorted": false,
          "empty": false
        },
        "pageNumber": 0,
        "pageSize": 10,
        "offset": 0,
        "paged": true,
        "unpaged": false
      },
      "totalPages": 5,
      "totalElements": 50,
      "last": false,
      "first": true,
      "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
      },
      "number": 0,
      "numberOfElements": 10,
      "size": 10,
      "empty": false
    }
  }
  ```

## 教师管理API

### 分页获取所有教师

- **URL**: `/api/teacher/page`
- **方法**: GET
- **描述**: 分页获取系统中所有教师的列表
- **参数**:
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**: 与学生分页API类似，返回教师信息的分页结果

## 课程管理API

### 分页获取所有课程

- **URL**: `/api/course/page`
- **方法**: GET
- **描述**: 分页获取系统中所有课程的列表
- **参数**:
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**: 返回课程信息的分页结果

### 分页根据名称搜索课程

- **URL**: `/api/course/search/page`
- **方法**: GET
- **描述**: 分页通过课程名称搜索相关课程
- **参数**:
  - `name`: 课程名称（必填）
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**: 返回符合搜索条件的课程信息的分页结果

### 分页查询教师的课程

- **URL**: `/api/course/teacher/{teacherId}/page`
- **方法**: GET
- **描述**: 分页获取指定教师教授的所有课程
- **参数**:
  - `teacherId`: 教师ID（必填，路径参数）
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**: 返回指定教师教授的课程信息的分页结果

## 成绩管理API

### 分页获取所有成绩

- **URL**: `/api/score/page`
- **方法**: GET
- **描述**: 分页获取系统中所有成绩的列表
- **参数**:
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**: 返回成绩信息的分页结果

### 分页查询学生的所有成绩

- **URL**: `/api/score/student/{studentId}/page`
- **方法**: GET
- **描述**: 分页获取指定学生的所有课程成绩
- **参数**:
  - `studentId`: 学生ID（必填，路径参数）
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**: 返回指定学生的成绩信息的分页结果

### 分页查询课程的所有成绩

- **URL**: `/api/score/course/{courseId}/page`
- **方法**: GET
- **描述**: 分页获取指定课程的所有学生成绩
- **参数**:
  - `courseId`: 课程ID（必填，路径参数）
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**: 返回指定课程的成绩信息的分页结果

### 分页查询教师的所有课程成绩

- **URL**: `/api/score/teacher/{teacherId}/page`
- **方法**: GET
- **描述**: 分页获取指定教师教授的所有课程的学生成绩
- **参数**:
  - `teacherId`: 教师ID（必填，路径参数）
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**: 返回指定教师课程的成绩信息的分页结果

## 用户管理API

### 分页获取所有用户

- **URL**: `/api/user/page`
- **方法**: GET
- **描述**: 分页获取系统中所有用户的列表
- **参数**:
  - `page`: 页码，从0开始（可选，默认为0）
  - `size`: 每页大小（可选，默认为20）
  - `sort`: 排序字段（可选，例如`id,desc`）
- **响应**: 返回用户信息的分页结果（不包含密码）

## 使用示例

### 请求示例

获取第一页（每页10条）的学生列表，按ID降序排序：

```
GET /api/student/page?page=0&size=10&sort=id,desc
```

### 响应示例

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "content": [
      {
        "id": 50,
        "name": "王五",
        "studentNo": "2023050",
        // 其他学生信息...
      },
      // 更多学生...
    ],
    "pageable": {
      "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
      },
      "pageNumber": 0,
      "pageSize": 10,
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "totalPages": 5,
    "totalElements": 50,
    "last": false,
    "first": true,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "number": 0,
    "numberOfElements": 10,
    "size": 10,
    "empty": false
  }
}
``` 