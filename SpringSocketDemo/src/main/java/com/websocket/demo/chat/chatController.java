package com.websocket.demo.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import static com.websocket.demo.chat.ChatMessage.*;

@Controller
public class chatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        ChatMessage.addPeoples();
        var chat = ChatMessage.builder().content(chatMessage.getContent())
                .timestamp(chatMessage.getTimestamp()).sender(chatMessage.getSender()).type(MessageType.JOIN)
                .count(getNumberOfPeople()).build();

        System.out.println(getNumberOfPeople());
        return chat;

    }
}
