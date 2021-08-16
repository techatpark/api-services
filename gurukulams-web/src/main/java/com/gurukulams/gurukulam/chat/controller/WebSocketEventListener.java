package com.gurukulams.gurukulam.chat.controller;

import com.gurukulams.gurukulam.chat.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


/**
 * Event Listener for Chat.
 */
@Component
public class WebSocketEventListener {
    /**
     * declaring logger.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(WebSocketEventListener.class);

    /**
     * autowire SimpMessageSendingOperations.
     */
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    /**
     * Handle web socket connect listener.
     *
     * @param event the event
     */
    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectedEvent
                                                       event) {
        LOGGER.info("Received a new web socket connection");
    }

    /**
     * Handle web socket disconnect listener.
     *
     * @param event the event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent
                                                          event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor
                .wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes()
                .get("username");
        if (username != null) {
            LOGGER.info("User Disconnected : " + username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
