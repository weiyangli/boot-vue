package com.dubbo.demo.provider.service.impl;

import com.dubbo.demo.provider.service.SpiService;

public class SpiServiceImpl implements SpiService {
    @Override
    public void syaHello() {
        System.out.println("spi1 say hello");
    }
}
