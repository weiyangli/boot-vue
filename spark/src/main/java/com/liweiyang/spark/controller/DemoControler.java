package com.liweiyang.spark.controller;

import com.alibaba.fastjson.JSONObject;
import com.liweiyang.spark.bean.Demo;
import com.liweiyang.spark.bean.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class DemoControler {

    @RequestMapping("/")
    public String index() throws Exception {
        //创建fm的配置
        Configuration config = new Configuration( new Version(1));
        //指定默认编码格式
        config.setDefaultEncoding("UTF-8");
        //设置模版文件的路径
        config.setClassForTemplateLoading(DemoControler.class, "/ftl");
        //获得模版包
        Template template = config.getTemplate("active.ftl");
        //从参数文件中获取指定输出路径 ,路径示例：C:/Workspace/shop-test/src/main/webapp/html
        String path = "C:\\Users\\admin\\Desktop";
        //定义输出流，注意必须指定编码
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path + "/" + "active.html")),"UTF-8"));
        //生成模版
        Map<String, String> map = new HashMap<>();
        map.put("name", "张三");
        template.process(map, writer);
        return "index";
    }

    @GetMapping("/demo/find")
    @ResponseBody
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
        return demoList;
    }
}
