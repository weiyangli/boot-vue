package com.boot.bvserver.service.impl;

import com.boot.bvserver.bean.ChatGroup;
import com.boot.bvserver.bean.Message;
import com.boot.bvserver.bean.MessageType;
import com.boot.bvserver.dao.MessageDao;
import com.boot.bvserver.service.MessageService;
import com.boot.bvserver.util.IdWorker;
import com.boot.bvserver.util.SecurityUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MessageDao messageDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 插入用户聊天消息
     *
     * @param message  消息
     */
    @Override
    public void insertUserMessage(Message message){
        // mongoTemplate 插入的时间是 UTC 时区比我们慢 8 小时
        Date now = DateUtils.addHours(new Date(), 8);
        message.setId(idWorker.nextId()).setDate(now).setValidityDate(DateUtils.addDays(now, 7));
        mongoTemplate.save(message, MessageType.getValueByCode(message.getType()));
    }

    /**
     * 拉取当前用户 7 天内和指定接收人聊天的消息
     *
     * @param  chatId  聊天窗口 id
     * @return
     */
    @Override
    public List<Message> pullMessages(String chatId, int type) {
        Criteria criteria = Criteria.where("chatId").is(chatId)
                .and("validityDate").gt(DateUtils.addHours(new Date(), 8));
        return mongoTemplate.find(Query.query(criteria), Message.class, MessageType.getValueByCode(type));
    }

    /**
     * 创建小组
     *
     * @param chatGroup
     */
    @Override
    public ChatGroup createdGroup(ChatGroup chatGroup){
        // 1. 创建聊天组
        // 2. 建立用户和小组关系

        // [1] 创建聊天组
        chatGroup.setId(idWorker.nextId());
        chatGroup.setCreatedBy(SecurityUtils.getLoginUserId());
        chatGroup.setUpdatedBy(SecurityUtils.getLoginUserId());
        messageDao.createdGroup(chatGroup);

        // [2] 建立用户和小组关系
        messageDao.createdUserGroups(chatGroup.getUserIds(), chatGroup.getId());
        return chatGroup;
    }

    /**
     * 查询当前用户加入的所有群聊
     *
     * @return
     */
    @Override
    public List<ChatGroup> findGroupByUserId() {
        return messageDao.findGroupByUserId(SecurityUtils.getLoginUserId());
    }
}
