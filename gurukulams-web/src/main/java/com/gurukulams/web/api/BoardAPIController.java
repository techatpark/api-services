package com.gurukulams.web.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.core.model.Board;
import com.gurukulams.core.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@Tag(name = "Boards", description = "Resource to manage Board")
class BoardAPIController {

    /**
     * declare a board service.
     */
    private final BoardService boardService;

    BoardAPIController(final BoardService aBoardService) {
        this.boardService = aBoardService;
    }

    /**
     * Create response entity.
     *
     * @param principal the principal
     * @param board  the board name
     * @return the response entity
     */
    @Operation(summary = "Creates a new board",
            description = "Can be called "
                    + "only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "board created successfully"),
            @ApiResponse(responseCode = "400",
                    description = "board is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Board> create(final Principal principal,
                                        @RequestBody final Board board) {
        Board created = boardService.create(principal.getName(), board);
        return ResponseEntity.created(URI.create("/api/board" + created.id()))
                .body(created);
    }

    /**
     * Read a board.
     * @param id
     * @param principal
     * @return a board
     */
    @Operation(summary = "Get the Board with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "getting board successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})

    @GetMapping("/{id}")
    public ResponseEntity<Board> read(@PathVariable final Long id,
                                         final Principal principal) {
        return ResponseEntity.of(boardService.read(principal.getName(), id));
    }

    /**
     * Update a Board.
     * @param id
     * @param principal
     * @param board
     * @return a board
     * @throws JsonProcessingException
     */
    @Operation(summary = "Updates the board by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "board updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "board is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})
    @PutMapping(value = "/{id}", produces = "application/json", consumes =
            "application/json")
    public ResponseEntity<Board> update(@PathVariable final Long id,
                                           final Principal
                                                   principal,
                                        @RequestBody final Board
                                                       board)
            throws JsonProcessingException {
        final Board updatedBoard =
                boardService.update(id, principal.getName(), board);
        return updatedBoard == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatedBoard);
    }

    /**
     * Delete a Board.
     * @param id
     * @param principal
     * @return board
     */
    @Operation(summary = "Deletes the board by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "board deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "board not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final
                                           Long id,
                                       final Principal principal) {
        return boardService.delete(principal.getName(),
                id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * List the Boards.
     * @param principal
     * @return list of board
     */
    @Operation(summary = "lists the board",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the board"),
            @ApiResponse(responseCode = "204",
                    description = "board are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Board>> list(final Principal
                                                       principal) {
        final List<Board> boardList = boardService.list(
                principal.getName());
        return boardList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(boardList);
    }

}
