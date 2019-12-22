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

    private Long chatId;         // 当前聊天窗口唯一 id (两人聊天为接收人 userId, 群组聊天为群组 groupId)

    /**
     * 推送的标题
     */
    private String title;

    /**
     * 推送的内容
     */
    private String content;


    public PushMessage(Long chatId, String title, String content) {
        this.chatId = chatId;
        this.title = title;
        this.content = content;
    }

}
