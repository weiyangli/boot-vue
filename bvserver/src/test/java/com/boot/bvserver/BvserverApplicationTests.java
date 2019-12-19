package com.boot.bvserver;

import com.boot.bvserver.service.impl.Threads;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BvserverApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            Thread thread = new Thread(new Threads());
            thread.start();
        }

    }

}
