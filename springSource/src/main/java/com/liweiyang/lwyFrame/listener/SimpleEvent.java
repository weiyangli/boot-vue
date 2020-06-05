package com.liweiyang.lwyFrame.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class SimpleEvent extends ApplicationContextEvent {

    public SimpleEvent(ApplicationContext source) {
        super(source);
    }
}
