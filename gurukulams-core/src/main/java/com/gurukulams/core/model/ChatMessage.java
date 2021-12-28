package com.gurukulams.core.model;

import java.time.LocalDateTime;

public record ChatMessage(Long id, Long chatId, Long senderId, Long recipientId,
                          String senderName, String recipientName,
                          String message,
                          LocalDateTime timestamp) {

}


