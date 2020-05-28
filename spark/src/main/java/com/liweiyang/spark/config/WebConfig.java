package com.liweiyang.spark.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final static String TARGET_FILE_ADDRESS = "/ftl/template/";

    /**
     * 静态文件映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ftl/template/**")
                .addResourceLocations("file:" + TARGET_FILE_ADDRESS + "//");
    }
}
