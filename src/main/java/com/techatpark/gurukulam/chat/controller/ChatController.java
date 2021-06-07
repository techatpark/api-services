package com.techatpark.gurukulam.chat.controller;

import com.techatpark.gurukulam.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Controller
public class ChatController {

    /**
     * Send message chat message.
     *
     * @param chatMessage the chat message
     * @return the chat message
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(final @Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    /**
     * Add user chat message.
     *
     * @param chatMessage    the chat message
     * @param headerAccessor the header accessor
     * @return the chat message
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(final @Payload ChatMessage chatMessage,
                               final SimpMessageHeaderAccessor
                                       headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username",
                chatMessage.getSender());
        return chatMessage;
    }

}
