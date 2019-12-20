package com.boot.bvserver.controller;

import com.boot.bvserver.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void sendMessage(String message) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.where("_id").equals(1);
        query.addCriteria(criteria);
        mongoTemplate.find(query, Message.class);
    }
}
