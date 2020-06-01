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

import java.util.Date;

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
        String code = getAccessToken("wx7c56bf0f29b168b8", "1eb5bf9d4ed4134b6b5e523c0f96553a");
        Room room = Room.builder().name("测试直播间").coverImg("xxxxxx").startTime(System.currentTimeMillis())
                .endTime(DateUtils.addDays(new Date(), 7).getTime())
                .anchorName("test1").anchorWechat("test1").shareImg(null)
                .build();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(JSON.toJSONString(room), header);
        String result = restTemplate.postForObject(String.format(Urls.API_GET_ROOM, code),  entity, String.class);
        return result;
    }
}
