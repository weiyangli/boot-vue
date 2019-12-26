package com.boot.bvserver.controller;

/**
 * 集中管理 URL
 */
public interface Urls {
    // 系统
    String PAGE_LOGIN = "/page/login";
    String PAGE_ACCESS = "/page/403";
    String PAGE_CODE = "/page/code/generator";

    // 用户
    String API_CURRENT_USER = "/api/get/current/user";   // 获取当前登录用户
    String API_USER = "/api/users";                      // 查询、添加、修改用户

    // 消息
    String API_MESSAGE = "/api/messages";                                 // 查询消息
    String API_SEND_MESSAGE = "/api/send/messages";                       // 发送消息
    String API_PULL_MESSAGE = "/api/pull/messages/{chatId}/{type}";       // 拉取消息

    // 上传文件
    String API_FILE_UPLOAD = "/api/file/upload";                          // 上传文件
}
