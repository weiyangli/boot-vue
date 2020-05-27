package com.aop.exception.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
// @Accessors chain = true set 方法返回當前對象，有点类似链式调用
//@Accessors(chain = true)
@Builder
public class Demo {
    private Long id;
    private String name;
    private JSONObject otherInfo;
    private List<User> userList;
}
