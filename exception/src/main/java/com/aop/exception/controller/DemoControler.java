package com.aop.exception.controller;

import com.alibaba.fastjson.JSONObject;
import com.aop.exception.aspect.annotation.AutoException;
import com.aop.exception.bean.Demo;
import com.aop.exception.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DemoControler {

    @RequestMapping("/demo/find")
    @ResponseBody
    @AutoException(value = "查询demo信息", operateType = 2)
    public List<Demo> findDemos() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enabled", "true");
        jsonObject.put("orderNum", "No458415151");
        jsonObject.put("phone", "15510557605");
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = User.builder().id(i + 1L).nickname("赵三").username("tony").build();
            users.add(user);
        }
        Demo demo = Demo.builder().id(4554544L).name("李明").otherInfo(jsonObject).userList(users).build();
        Demo demo2 = Demo.builder().id(89757L).name("找你你").otherInfo(jsonObject).userList(users).build();
        List<Demo> demoList = new ArrayList<>();
        demoList.add(demo);
        demoList.add(demo2);
        // 异常处理测试
        int a =  1 / 0;
        return demoList;
    }
}
