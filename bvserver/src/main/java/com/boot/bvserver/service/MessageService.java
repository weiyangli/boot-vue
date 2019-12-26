package com.boot.bvserver.service;


import com.boot.bvserver.bean.ChatGroup;
import com.boot.bvserver.bean.Message;

import java.util.List;

public interface MessageService {

     void insertUserMessage(Message message);

    /**
     * 拉取当前用户 7 天内和指定接收人聊天的消息
     *
     * @param  chatId  聊天窗口 id
     * @param  type    消息类型
     * @return
     */
     List<Message> pullMessages(String chatId, int type);

    /**
     * 创建小组
     *
     * @param chatGroup
     */
    ChatGroup createdGroup(ChatGroup chatGroup);

    /**
     * 查询当前用户加入的所有群聊
     *
     * @return
     */
    List<ChatGroup> findGroupByUserId();

}
