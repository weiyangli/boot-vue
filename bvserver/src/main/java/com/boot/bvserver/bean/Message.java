package com.boot.bvserver.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

/**
 * 即时聊天消息类
 */
@Data
@Accessors(chain = true)
@Document(collection = "message")
public class Message {

    private final static int VALIDITY_DAY = 7;  // 消息有效期
    @Id
    private Long id;
    private Long userId;                        // 发送人 id
    private Long receiveId;                     // 接收人 id(群组消息为 groupId)
    private String chatId;                      // 当前聊天窗口唯一 id (两人聊天为接收人id 拼接发送人 id, 群组聊天为群组 groupId)
    private String content;                     // 消息内容
    private String title;                       // 消息标题
    private Date date;                          // 创建时间
    private Date validityDate;                  // 有效期
    private int type;                           // 消息类型 1：个人消息 2：群组消息

    public Message() {

    }

    public Message(Long userId, String chatId, String title, String content, int type) {
        this.userId = userId;
        this.chatId = chatId;
        this.content = content;
        this.title = title;
        this.type = type;
    }

}
