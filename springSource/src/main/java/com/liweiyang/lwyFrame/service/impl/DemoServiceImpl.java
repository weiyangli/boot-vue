package com.liweiyang.lwyFrame.service.impl;

import com.liweiyang.lwyFrame.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Override
    public void login() {
        log.error("service");
    }
}
