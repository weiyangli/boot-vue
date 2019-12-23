package com.boot.bvserver;

import com.boot.bvserver.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BvserverApplicationTests {

    @Autowired
    private MessageService messageService;

    @Test
    public void contextLoads() {
    }

}
