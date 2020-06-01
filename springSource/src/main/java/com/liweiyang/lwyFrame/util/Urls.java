package com.liweiyang.lwyFrame.util;

/**
 * 微信小程序直播接口相关请求地址
 *
 */
public interface Urls {
    /**获取小程序全局唯一后台接口调用凭据（access_token）**/
    String API_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**创建直播间**/
    String API_GET_ROOM = "https://api.weixin.qq.com/wxaapi/broadcast/room/create?access_token=%s";
}
