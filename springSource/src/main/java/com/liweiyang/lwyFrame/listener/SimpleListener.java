package com.liweiyang.lwyFrame.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener  implements ApplicationListener<SimpleEvent> {

    @Override
    public void onApplicationEvent(SimpleEvent simpleEvent) {
        SimpleEvent event = simpleEvent;
        System.out.println("MailSender发送了邮件");
    }
}
