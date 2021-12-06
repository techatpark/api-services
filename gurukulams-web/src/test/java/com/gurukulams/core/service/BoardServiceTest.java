package com.gurukulams.core.service;

import com.gurukulams.core.model.Board;
import com.gurukulams.core.model.Syllabus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    /**
     * Before.
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void before() throws IOException {
        cleanUp();
    }

    /**
     * After.
     */
    @AfterEach
    void after() {
        cleanUp();
    }

    private void cleanUp() {
        boardService.deleteAll();
    }

    @Test
    void create(){
        final Board board = boardService.create("mani",
                anBoard());
        assertTrue(boardService.read("mani",board.id()).isPresent(),
                "Created Board");
    }

    @Test
    void read() {
        final Board board = boardService.create("mani",
                anBoard());
        final Long newBoardId = board.id();
        Assertions.assertTrue(boardService.read("mani", newBoardId).isPresent(),
                "Board Created");
    }

    @Test
    void update() {

        final Board board = boardService.create("mani",
                anBoard());
        final Long newBoardId = board.id();
        Board newBoard = new Board(null, "Board", "A " +
                "Board", null, "tom", null, null);
        Board updatedBoard = boardService
                .update(newBoardId, "manikanta", newBoard);
        assertEquals("Board", updatedBoard.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boardService
                    .update(10000L, "manikanta", newBoard);
        });
    }

    @Test
    void delete() {

        final Board board = boardService.create("mani",
                anBoard());
        boardService.delete("mani",board.id());
        assertFalse(boardService.read("mani",board.id()).isPresent(),
                "Deleted Board");

    }

    /**
     * Gets board.
     *
     * @return the board
     */
    Board anBoard() {

        Board board = new Board(null, "State Board",
                "A " + "Board", null, null,
                null, null);
        return board;
    }
}
