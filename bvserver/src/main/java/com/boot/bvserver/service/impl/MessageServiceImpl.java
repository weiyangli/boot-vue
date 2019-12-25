package com.boot.bvserver.service.impl;

import com.boot.bvserver.bean.Message;
import com.boot.bvserver.service.MessageService;
import com.boot.bvserver.util.IdWorker;
import com.boot.bvserver.util.SecurityUtils;
import com.boot.bvserver.util.Utils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    /**
     * 插入用户聊天消息
     *
     * @param message  消息
     */
    public void insertUserMessage(Message message){
        message.setId(idWorker.nextId()).setDate(new Date()).setValidityDate(DateUtils.addDays(new Date(), 7));
        mongoTemplate.save(message, message.getType() == 1 ? Message.MESSAGE_USER : Message.MESSAGE_GROUP);
    }

    /**
     * 拉取当前用户 7 天内和指定接收人聊天的消息
     *
     * @param  receiveId  接收人 id
     * @return
     */
    public List<Message> pullMessage(Long receiveId) {
        Criteria criteria = Criteria.where("receiveId").is(receiveId)
                .and("userId").is(1l)
                .and("validityDate").gt(new Date());
        List<Message> groupMessages = mongoTemplate.find(Query.query(criteria), Message.class, Message.MESSAGE_GROUP);
        List<Message> userMessages = mongoTemplate.find(Query.query(criteria), Message.class, Message.MESSAGE_USER);
        groupMessages.addAll(userMessages);
        return groupMessages;
    }
}
