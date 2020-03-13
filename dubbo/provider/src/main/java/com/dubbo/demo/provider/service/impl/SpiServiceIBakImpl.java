package com.dubbo.demo.provider.service.impl;

import com.dubbo.demo.provider.service.SpiService;

public class SpiServiceIBakImpl implements SpiService {
    @Override
    public void syaHello() {
        System.out.println("spi2 say hello");
    }
}
