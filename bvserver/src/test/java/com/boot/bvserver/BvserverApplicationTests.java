package com.boot.bvserver;

import com.boot.bvserver.bean.Message;
import com.boot.bvserver.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BvserverApplicationTests {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void mongodbTest() {
        List<Message> messageList = messageService.pullMessage(2l);
        System.out.println("---------");
    }

}
