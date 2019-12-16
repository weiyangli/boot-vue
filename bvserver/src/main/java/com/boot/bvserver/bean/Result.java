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
public class Result {
    private int code;        // 错误编码
    private String desc;     // 描述
    private Object data;     // 返回结果

    public Result() {

    }
    public Result(int code, String desc, Object data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }
    public static Result ok() {
        return new Result().setCode(0).setDesc("请求成功");
    }
    public static Result ok(Object data) {
        return new Result().setCode(0).setDesc("请求成功").setData(data);
    }
    public static Result ok(int code, String desc) {
        return new Result().setCode(code).setDesc(desc);
    }
    public static Result ok(int code, String desc, Object data) {
        return new Result().setCode(code).setDesc(desc).setData(data);
    }
    public static Result ok(ResultEnum resultEnum, Object data) {
        return new Result().setCode(resultEnum.getCode()).setDesc(resultEnum.getDesc()).setData(data);
    }
    public static Result fail() {
        return new Result().setCode(500).setDesc("请求失败");
    }
    public static Result fail(String desc) {
        return new Result().setDesc(desc);
    }
    public static Result fail(int code, String desc) {
        return new Result().setCode(code).setDesc(desc);
    }
    public static Result fail(int code, String desc, Object data) {
        return new Result().setCode(code).setDesc(desc).setData(data);
    }
    public static Result fail(ResultEnum resultEnum) {
        return new Result().setCode(resultEnum.getCode()).setDesc(resultEnum.getDesc());
    }
}
