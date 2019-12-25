package com.boot.bvserver.service;


import com.boot.bvserver.bean.Message;

import java.util.List;

public interface MessageService {

     void insertUserMessage(Message message);

    /**
     * 拉取当前用户 7 天内和指定接收人聊天的消息
     *
     * @param  receiveId  接收人 id
     * @return
     */
     List<Message> pullMessage(Long receiveId);
}
