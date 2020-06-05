package com.liweiyang.lwyFrame.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liweiyang.lwyFrame.bean.Room;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 微信直播 API 接口工具类
 *
 *
 */
@Component
@Slf4j
public class WeChatLiveUtil {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）
     *
     * @param appid
     * @param secret
     * @return
     */
    public String getAccessToken(String appid, String secret) {
        String json = restTemplate.getForObject(String.format(Urls.API_ACCESS_TOKEN, appid, secret), String.class);
        JSONObject obj = JSONObject.parseObject(json);
        String code = null;
        if (obj != null) {
            try {
                code = StringUtils.isNotBlank(obj.getString("access_token")) ? obj.getString("access_token") : null;
            } catch (Exception e) {
                log.error("ddd");
            }
        }
        return code;
    }

    public String getRestPost() {
        String code = getAccessToken("wxca67c7e0126a94d8", "ff2a083202c024a5dd00f6ae4e3b9c04");
        java.util.Date startTime = DateUtils.addHours(new Date(), 5);
        Room room = Room.builder().name("测试直播间").coverImg("xxxxxx").startTime(startTime.getTime())
                .endTime(DateUtils.addHours(startTime, 2).getTime())
                .anchorName("test1").anchorWechat("test1").shareImg(null)
                .build();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        System.out.println(JSON.toJSONString(room));
        HttpEntity<String> entity = new HttpEntity<String>(JSON.toJSONString(room), header);
        String result = restTemplate.postForObject(String.format(Urls.API_GET_ROOM, code),  entity, String.class);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.addHours(new Date(), 2).getTime() / 1000);
        System.out.println(DateUtils.addHours(new Date(), 5).getTime() / 1000);
        String a = "111";
        String strs = "1111,43534g,trtyhrt";
        if (Optional.ofNullable(strs).isPresent()) {
            String c = Arrays.stream(strs.split(",")).filter(x -> !x.equals(a)).collect(Collectors.joining(","));
            System.out.println(c);
        }

    }
}
