package com.boot.bvserver.controller;

import com.boot.bvserver.bean.Message;
import com.boot.bvserver.bean.Result;
import com.boot.bvserver.service.MessageService;
import com.boot.bvserver.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {


    @Autowired
    private MessageService messageService;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @GetMapping(Urls.API_SEND_MESSAGE)
    public Result sendMsg(@RequestParam("userId")  Long userId,
                          @RequestParam("chatId")  String chatId,
                          @RequestParam("content") String content,
                          @RequestParam("type")    int type){
        // messageService.pushMessageToUser(new Message(userId, chatId,"", content, type));
        return Result.ok();
    }

    @MessageMapping("/chat")
    public void sendMessage(@RequestBody Message message) {
        messagingTemplate.convertAndSendToUser( message.getChatId(), "/topic/chat", message);
        // 消息插入到 mongodb
        messageService.insertUserMessage(message);
    }
}
