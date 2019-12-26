package com.boot.bvserver.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatGroup extends BaseEntity{
    private String groupName; // 小组名称
    private String picture;   // 群头像
    private Long [] userIds;  // 群里所有用户的 id
}
