package com.boot.bvserver.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
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
    private Long id;                            // 消息 ID: 注解 @Id 对应 MongoDB 的 _id
    private String message;                     // 消息内容
    private Date date;                          // 创建时间
    private int groupId;                        // 群组 id
    private Date validityDate;                  // 有效期
    private int type;                           // 消息类型 1：个人消息 2：群组消息

    // 有效期设置为 7 天
    public void setValidityDate() {
        this.validityDate = DateUtils.addDays(new Date(), 7);
    }

}
