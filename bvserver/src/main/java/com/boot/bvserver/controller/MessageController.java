package com.boot.bvserver.controller;

import com.boot.bvserver.bean.Message;
import com.boot.bvserver.bean.Result;
import com.boot.bvserver.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {


    @Autowired
    private MessageService messageService;

    @GetMapping(Urls.API_SEND_MESSAGE)
    public Result sendMsg(@RequestParam("userId")  Long userId,
                          @RequestParam("chatId")  String chatId,
                          @RequestParam("content") String content,
                          @RequestParam("type")    int type){
        messageService.pushMessageToUser(new Message(userId, chatId,"", content, type));
        return Result.ok();
    }
}
