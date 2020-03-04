package com.boot.bvserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created by sang on 2018/1/27.
 */
@Configuration
// 用于开启使用STOMP协议来传输基于代理（MessageBroker）的消息，
// 这时候控制器（controller）开始支持@MessageMapping,就像是使用@requestMapping一样。
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 这个方法的作用是添加一个服务端点，来接收客户端的连接。
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // setAllowedOrigins允许所有域连接，否则浏览器可能报403错误
        registry.addEndpoint("/ws/chat/point").setAllowedOrigins("*").addInterceptors().withSockJS();
    }

    // 这个方法的作用是定义消息代理，通俗一点讲就是设置消息连接请求的各种规范信息
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 表示客户端订阅地址的前缀信息，也就是客户端接收服务端消息的地址的前缀信息
        // registry.enableSimpleBroker("/topic");
        // 指服务端接收地址的前缀，意思就是说客户端给服务端发消息的地址的前缀
        registry.setApplicationDestinationPrefixes("/ws");
    }

}
