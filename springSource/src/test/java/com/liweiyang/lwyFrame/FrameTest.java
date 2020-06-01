package com.liweiyang.lwyFrame;

import com.alibaba.fastjson.JSONObject;
import com.liweiyang.lwyFrame.bean.Demo;
import com.liweiyang.lwyFrame.util.WeChatLiveUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/application.xml" })
public class FrameTest {

    @Autowired
    private WeChatLiveUtil weChatLiveUtil;

    @Test
    public void springContextTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/application.xml");
        Demo demo = ctx.getBean("demo", Demo.class);
        System.out.println(JSONObject.toJSONString(demo));
    }

    @Test
    public void getAccessToken() {
        String code =  weChatLiveUtil.getRestPost();
        System.out.println(code);
    }
}
