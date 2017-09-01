package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.app.msg.ChatMessage;
import com.app.msg.SysMessage;

@RestController
public class MsgController {

    Logger logger = LoggerFactory.getLogger(MsgController.class);

    @Autowired
    UserHelper userHelper;

    @MessageMapping("/u/{toId}")
    @SendTo("/u/{toId}")
    public ChatMessage chatMessage(ChatMessage chatMessage) {
        logger.info("chatMessage: " + chatMessage);
        return chatMessage;
    }

    @MessageMapping("/sys/users")
    @SendTo("/sys/users")
    public SysMessage sysMessage() {
        System.out.println("MsgController.sysMessage()" + userHelper.getUsers());
        return new SysMessage("好友上线！", userHelper.getUsers());
    }

}
