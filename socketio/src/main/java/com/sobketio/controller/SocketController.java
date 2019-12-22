package com.sobketio.controller;

import com.sobketio.bean.PushMessage;
import com.sobketio.service.SocketIoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/socket")
public class SocketController {


    @Autowired
    private SocketIoService socketIoService;

    @GetMapping("/send")
    public String sendMsg(@RequestParam("content")String content, @RequestParam("loginUser")String loginUser){
        socketIoService.pushMessageToUser(new PushMessage(loginUser,"",content));
        return "OK";
    }
}
