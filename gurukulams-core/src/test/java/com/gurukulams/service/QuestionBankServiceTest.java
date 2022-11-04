package com.gurukulams.service;

import com.gurukulams.core.util.BoardMaker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionBankServiceTest {

    @Autowired
    private BoardMaker boardMaker;

    @Test
    void testSetup() {
        boardMaker.createAllBoards("tom");
    }
}
