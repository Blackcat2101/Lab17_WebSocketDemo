package com.websocket.demo.config;

import com.websocket.demo.chat.ChatMessage;
import com.websocket.demo.chat.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        int currentPeople = ChatMessage.getNumberOfPeople();
        ChatMessage.deletePeoples();
        if (username != null) {
            var chatMessage = ChatMessage.builder().type(MessageType.LEAVE).sender(username)
                    .count(ChatMessage.getNumberOfPeople()).build();
            System.out.println(ChatMessage.getNumberOfPeople());
            messageSendingOperations.convertAndSend("/topic/public", chatMessage);
        }
    }
}

