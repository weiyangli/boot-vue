package com.boot.bvserver.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 请求返回实体类
 *
 */
@Getter
@Setter
@Accessors(chain = true)
public class Result<T> {
    private int code;        // 错误编码
    private String desc;     // 描述
    private boolean success; // 成功状态
    private T data;          // 返回结果

    public Result() {  }
    public Result(int code, String desc, boolean success, T data) {
        this.code = code;
        this.desc = desc;
        this.success = success;
        this.data = data;
    }
    public static Result ok() {
        return new Result().setCode(0).setDesc("请求成功").setSuccess(true);
    }
    public static Result ok(Object data) {
        return new Result().setCode(0).setDesc("请求成功").setData(data).setSuccess(true);
    }
    public static Result reqOkEnum(ResultEnum resultEnum) {
        return new Result().setCode(resultEnum.getCode()).setDesc(resultEnum.getDesc()).setSuccess(true);
    }
    public static Result fail() {
        return new Result().setCode(500).setDesc("请求失败").setSuccess(false);
    }
    public static Result fail(String desc) {
        return new Result().setCode(500).setDesc(desc).setSuccess(false);
    }
    public static Result fail(int code, String desc) {
        return new Result().setCode(code).setDesc(desc).setSuccess(false);
    }
    public static Result reqFailEnum(ResultEnum resultEnum) {
        return new Result().setCode(resultEnum.getCode()).setDesc(resultEnum.getDesc()).setSuccess(false);
    }
}