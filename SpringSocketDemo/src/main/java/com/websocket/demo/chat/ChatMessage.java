package com.websocket.demo.chat;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessage {
    private String content;
    private String timestamp;
    private String sender;
    private MessageType type;
    private static int peoples;
    private int count;

    public static Integer getNumberOfPeople() {
        return peoples;
    }

    public static void addPeoples(){
        peoples++;
    }
    public static void deletePeoples(){
        peoples--;
    }

}




