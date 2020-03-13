package com.dubbo.demo.provider.service;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface SpiService {
    void syaHello();
}
