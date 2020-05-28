package com.liweiyang.lwyFrame;

import com.alibaba.fastjson.JSONObject;
import com.liweiyang.lwyFrame.bean.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/application.xml" })
public class FrameTest {

    @Test
    public void springContextTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/application.xml");
        Demo demo = ctx.getBean("demo", Demo.class);
        System.out.println(JSONObject.toJSONString(demo));
    }
}
