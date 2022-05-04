package com.gurukulams.service;

import com.gurukulams.core.model.Board;
import com.gurukulams.core.service.BoardService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
        final Board board = boardService.create("mani",null,
                anBoard());
        Assertions.assertTrue(boardService.read("mani",null,board.id()).isPresent(),
                "Created Board");
    }

    @Test
    void read() {
        final Board board = boardService.create("mani",null,
                anBoard());
        final Long newBoardId = board.id();
        Assertions.assertTrue(boardService.read("mani",null, newBoardId).isPresent(),
                "Board Created");
    }

    @Test
    void update() {

        final Board board = boardService.create("mani",null,
                anBoard());
        final Long newBoardId = board.id();
        Board newBoard = new Board(null, "Board", "A " +
                "Board", null, "tom", null, null);
        Board updatedBoard = boardService
                .update(newBoardId, "mani", null, newBoard);
        Assertions.assertEquals("Board", updatedBoard.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boardService
                    .update(10000L, "mani", null,newBoard);
        });
    }

    @Test
    void delete() {

        final Board board = boardService.create("mani",null,
                anBoard());
        boardService.delete("mani",board.id());
        Assertions.assertFalse(boardService.read("mani",null,board.id()).isPresent(),
                "Deleted Board");

    }

    @Test
    void list() {

        final Board board = boardService.create("mani",null,
                anBoard());
        Board newBoard = new Board(null, "Board New", "A " +
                "Board", null, "tom", null, null);
        boardService.create("mani",null,
                newBoard);
        List<Board> listofboard = boardService.list("manikanta");
        Assertions.assertEquals(2, listofboard.size());

    }

    @Test
    void testLocalization() {
        // Create a Board
        final Board board = boardService.create("mani",null,
                anBoard());

        // Update for China Language
        boardService.update(board.id(),"mani", Locale.CHINA,anBoard(board,
                "Chinese Title",
                "Chinese Description"));

        // Get for China Language
        Optional<Board> createBoard = boardService.read("mani",Locale.CHINA,
                board.id());
        Assertions.assertEquals("Chinese Title", createBoard.get().title());
        Assertions.assertEquals("Chinese Description", createBoard.get().description());

        // Get for France which does not have data
        createBoard = boardService.read("mani",Locale.FRANCE,
                board.id());
        Assertions.assertEquals("State Board", createBoard.get().title());
        Assertions.assertEquals("State Board Description", createBoard.get().description());

    }

    /**
     * Gets board.
     *
     * @return the board
     */
    Board anBoard() {
        Board board = new Board(null, "State Board",
                "State Board Description", null, null,
                null, null);
        return board;
    }

    /**
     * Gets board from reference board.
     *
     * @return the board
     */
    Board anBoard(final Board ref,final String title,final String description) {
        return new Board(ref.id(), title,
                description, ref.created_at(), ref.created_by(),
                ref.modified_at(), ref.modified_by());
    }
}
