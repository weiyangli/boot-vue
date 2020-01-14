package com.boot.bvserver.controller;

import com.alibaba.fastjson.JSON;
import com.boot.bvserver.bean.ChatGroup;
import com.boot.bvserver.bean.Message;
import com.boot.bvserver.bean.Result;
import com.boot.bvserver.service.MessageService;
import com.boot.bvserver.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@Slf4j
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class.getName());

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
        log.info("插入消息{}", JSON.toJSONString(message));
        messagingTemplate.convertAndSendToUser( message.getChatId(), "/topic/chat", message);
        // 消息插入到 mongodb
        messageService.insertUserMessage(message);
    }

    /**
     * 拉取当前用户 7 天内和指定接收人聊天的消息
     *
     * @param  chatId  聊天窗口 id
     * @param  type    消息类型 1： 普通消息 2：群组消息
     * @return
     */
    @GetMapping(Urls.API_PULL_MESSAGE)
    @ResponseBody
    public Result<List<Message>> pullMessages(@PathVariable("chatId") String chatId,
                                              @PathVariable("type")   int type) {
        logger.info("拉取{}的消息", chatId);
        return Result.ok(messageService.pullMessages(chatId, type));
    }

    /**
     * 创建小组
     *
     * @param chatGroup
     */
    @PostMapping(Urls.API_MESSAGE)
    @ResponseBody
    public Result createdGroup(@RequestBody ChatGroup chatGroup){
        return Result.ok(messageService.createdGroup(chatGroup));
    }

    /**
     * 查询当前用户加入的所有群聊
     *
     * @return
     */
    @GetMapping(Urls.API_MESSAGE)
    @ResponseBody
    public Result<List<ChatGroup>> findGroupByUserId() {
        return  Result.ok(messageService.findGroupByUserId());
    }
}
