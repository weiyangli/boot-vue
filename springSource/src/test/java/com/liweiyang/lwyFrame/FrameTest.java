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

    private static String appIdMine = "wx0ddaf0c9107b7561";

    private static final String AppSecretMine = "6e47f8b56a7c6abfabb9b5105bd7ff3e";

    private static String appIdOther = "wxca67c7e0126a94d8";

    private static final String AppSecretOther = "ff2a083202c024a5dd00f6ae4e3b9c04";

    @Test
    public void springContextTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/application.xml");
        Demo demo = ctx.getBean("demo", Demo.class);
        System.out.println(JSONObject.toJSONString(demo));
    }

    @Test
    public void getAccessToken() {
        String newCode = weChatLiveUtil.getAccessToken(appIdOther, AppSecretOther);
//        String code =  weChatLiveUtil.getRestPost();
        System.out.println(newCode);
    }
}
