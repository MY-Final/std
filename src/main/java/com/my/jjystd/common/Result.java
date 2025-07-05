package com.my.jjystd.common;

import lombok.Data;

/**
 * 统一API响应结果封装
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param message 提示信息
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(200, message, data);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> failed(String message) {
        return new Result<>(500, message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> failed() {
        return failed("操作失败");
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> Result<T> validateFailed() {
        return failed("参数检验失败");
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> validateFailed(String message) {
        return new Result<>(400, message, null);
    }

    /**
     * 未找到资源返回结果
     */
    public static <T> Result<T> notFound() {
        return new Result<>(404, "请求资源不存在", null);
    }

    /**
     * 未授权返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> unauthorized(String message) {
        return new Result<>(401, message, null);
    }
} 