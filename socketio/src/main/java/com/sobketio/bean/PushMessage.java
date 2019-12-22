package com.sobketio.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author linzf
 * @since 2019-06-13
 * 类描述：socket消息发送实体类
 */
@Data
@Accessors(chain = true)
public class PushMessage {

    /**
     * 当前登陆的用户
     */
    private String loginUser;

    /**
     * 推送的标题
     */
    private String title;

    /**
     * 推送的内容
     */
    private String content;


    public PushMessage(String loginUser, String title, String content) {
        this.loginUser = loginUser;
        this.title = title;
        this.content = content;
    }

}
