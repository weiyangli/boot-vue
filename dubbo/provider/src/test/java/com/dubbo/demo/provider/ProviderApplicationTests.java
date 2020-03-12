package com.dubbo.demo.provider;

import com.dubbo.demo.provider.service.SpiService;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ServiceLoader;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProviderApplicationTests {

    @Test
   public void contextLoads() {
        ServiceLoader<SpiService> serviceLoader = ServiceLoader.load(SpiService.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(SpiService::syaHello);
    }

    @Test
   public void say() {
        ExtensionLoader<SpiService> extensionLoader =
                ExtensionLoader.getExtensionLoader(SpiService.class);
        SpiService optimusPrime = extensionLoader.getExtension("SpiServiceIBakImpl");
        optimusPrime.syaHello();
        SpiService bumblebee = extensionLoader.getExtension("SpiServiceImpl");
        bumblebee.syaHello();
    }
}
