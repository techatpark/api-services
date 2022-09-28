package com.gurukulams.web.api;

import com.gurukulams.core.model.Board;
import com.gurukulams.core.model.Book;
import com.gurukulams.core.model.Grade;
import com.gurukulams.core.model.Subject;
import com.gurukulams.core.service.BoardService;
import com.gurukulams.core.service.BookService;
import com.gurukulams.core.service.GradeService;
import com.gurukulams.core.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/boards")
@Tag(name = "Boards", description = "Resource to manage Board")
class BoardAPIController {

    /**
     * declare a board service.
     */
    private final BoardService boardService;

    /**
     * declare a grade service.
     */
    private final GradeService gradeService;

    /**
     * declare a syllabus service.
     */
    private final SubjectService subjectService;

    /**
     * declare a bookservice.
     */
    private final BookService bookService;

    BoardAPIController(final BoardService aBoardService,
                       final GradeService agradeService,
                       final SubjectService asubjectService,
                       final BookService aBookService) {
        this.boardService = aBoardService;
        this.gradeService = agradeService;
        this.subjectService = asubjectService;
        this.bookService = aBookService;
    }

    /**
     * Create response entity.
     *
     * @param principal the principal
     * @param board  the board name
     * @param locale the locale
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
                    @RequestHeader(name = "Accept-Language", required = false)
                     final Locale locale,
                    @RequestBody final Board board) {
        Board created = boardService.create(principal.getName(), locale, board);
        return ResponseEntity.created(URI.create("/api/board" + created.id()))
                .body(created);
    }

    /**
     * Read a board.
     * @param id
     * @param principal
     * @param locale the locale
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
                      @RequestHeader(name = "Accept-Language", required = false)
                      final Locale locale,
                      final Principal principal) {
        return ResponseEntity.of(boardService.read(principal.getName(),
                                                            locale, id));
    }

    /**
     * Update a Board.
     * @param id
     * @param principal
     * @param locale
     * @param board
     * @return a board
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
                     @RequestHeader(name = "Accept-Language", required = false)
                                            final Locale locale,
                     @RequestBody final Board
                                                       board) {
        final Board updatedBoard =
                boardService.update(id, principal.getName(), locale, board);
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
     * @param locale
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
                                                       principal,
                                        @RequestHeader(name = "Accept-Language",
                                                required = false)
                                        final Locale locale) {
        final List<Board> boardList = boardService.list(
                principal.getName(), locale);
        return boardList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(boardList);
    }


    /**
     * List the Grades.
     * @param principal
     * @param locale
     * @param id
     * @return list of grades
     */
    @Operation(summary = "lists the grades with given  board id",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the grades with given board id"),
            @ApiResponse(responseCode = "204",
                    description = "grades are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping("/{id}/grades")
    public ResponseEntity<List<Grade>> list(final Principal principal,
                                            @RequestHeader
                                                    (name = "Accept-Language",
                                                            required = false)
                                            final Locale locale,
                                            @PathVariable final Long id) {
        final List<Grade> gradeList = gradeService.list(
                principal.getName(), locale, id);
        return gradeList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(gradeList);

    }


    /**
     * List the subjects as per board and grade.
     * @param principal
     * @param locale
     * @param boardId
     * @param gradeId
     * @return list of subjects
     */
    @Operation(summary = "lists the syllabus with given  board id and grade id",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the syllabus with given"
                     + " board id and grade id"),
            @ApiResponse(responseCode = "204",
                    description = "grades are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping("/{boardId}/grades/{gradeId}/subjects")
    public ResponseEntity<List<Subject>> list(final Principal principal,
                             final Locale locale,
                             @PathVariable final Long boardId,
                         @PathVariable final Long gradeId) {

        final List<Subject> subjectList = subjectService.list(
                principal.getName(), locale, boardId, gradeId);
        return subjectList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(subjectList);

    }

    /**
     * List the books as per board and grade.
     * @param principal
     * @param locale
     * @param boardId
     * @param gradeId
     * @param subjectId
     * @return list of syllabus
     */
    @Operation(summary = "lists the books with given  board id and grade id",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the books with given"
                    + " board id and grade id"),
            @ApiResponse(responseCode = "204",
                    description = "books are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping("/{boardId}/grades/{gradeId}/subjects/{subjectId}/books")
    public ResponseEntity<List<Book>> list(final Principal principal,
                                           final Locale locale,
                                           @PathVariable final Long boardId,
                                           @PathVariable final Long gradeId,
                                           @PathVariable final Long subjectId) {

        final List<Book> subjectList = bookService.list(
                principal.getName(), locale, boardId, gradeId, subjectId);
        return subjectList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(subjectList);
    }

}
