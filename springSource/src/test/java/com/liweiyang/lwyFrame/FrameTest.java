package com.liweiyang.lwyFrame;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liweiyang.lwyFrame.Dao.DemoDao;
import com.liweiyang.lwyFrame.bean.Demo;
import com.liweiyang.lwyFrame.bean.DemoBean;
import com.liweiyang.lwyFrame.util.WeChatLiveUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/application.xml" })
public class FrameTest {

    @Autowired
    private WeChatLiveUtil weChatLiveUtil;

    @Autowired
    private DemoDao demoDao;

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

    @Test
    public void testLocalDateTime() {
       List<DemoBean> demoBeanList = demoDao.findDemos();
       LocalDateTime localDateTime = demoBeanList.get(0).getDate().withNano(0);
       System.out.println(localDateTime.toString().replace("T", " "));
       System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
       System.out.println(JSON.toJSONString(demoBeanList));
    }
}
