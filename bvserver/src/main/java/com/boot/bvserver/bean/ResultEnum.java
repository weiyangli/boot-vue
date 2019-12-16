package com.boot.bvserver.bean;

/**
 * 请求返回常用状态枚举
 *
 */
public enum ResultEnum {
    // 系统
    LOGIN_SUCCESS(1000, "登陆成功!"),
    LOGIN_FAILURE(1001, "登陆失败"),
    LOGOUT_SUCCESS(1002, "注销成功!");

    private int code;        // 错误编码
    private String desc;     // 描述

    ResultEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据 code 获取 value
     *
     * @param code
     * @return
     */
    public static String getValueByCode(int code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (code == resultEnum.getCode()) {
                return resultEnum.getDesc();
            }
        }
        return null;
    }
}
