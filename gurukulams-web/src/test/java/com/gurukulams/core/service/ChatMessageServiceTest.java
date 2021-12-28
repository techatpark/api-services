package com.gurukulams.core.service;

import com.gurukulams.core.model.ChatMessage;
import com.gurukulams.core.model.Learner;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ChatMessageServiceTest {
    @Autowired
    private ChatMessageService chatMessageService;

    @BeforeEach
    void before() throws IOException {
        cleanup();
    }

    @AfterEach
    void after() throws IOException {
        cleanup();
    }

    private void cleanup() {
        chatMessageService.deleteAll();
    }

    @Test
    void create() {
        final ChatMessage chatMessage = chatMessageService.create("mani",
                achatMessage());
        assertTrue(chatMessageService.read("mani",chatMessage.id()).isPresent(),"Created ChatMessage");

    }

    @Test
    void read() {
        final ChatMessage chatMessage = chatMessageService.create("Manikanta",
                achatMessage());
        Assertions.assertTrue(chatMessageService.read("Manikanta",
                chatMessage.id()).isPresent(), "ChatMessage Found");
    }

    @Test
    void delete() {
        final ChatMessage chatMessage = chatMessageService.create("Manikanta",
                achatMessage());
        chatMessageService.delete("mani",chatMessage.id());
        assertFalse(chatMessageService.read("mani",chatMessage.id()).isPresent(),"Deleted ChatMessage");
    }

    @Test
    void list() {
        final ChatMessage chatMessage = chatMessageService.create("Manikanta",
                achatMessage());
        ChatMessage chatMessage1=new ChatMessage(null, 2L,25L,
                35L, "Priya", "Hari", "Hi, Hari", null);
        chatMessageService.create("Manikanta", chatMessage1);
        List<ChatMessage> listOfMessage = chatMessageService.list("Manikanta");
        Assertions.assertEquals(2, listOfMessage.size());
    }


    ChatMessage achatMessage() {
        ChatMessage chatMessage=new ChatMessage(null,1L,
                20L,
                30L,
                "Hari","Priya", "Hello,Priya", null);
        return chatMessage;
    }
}
