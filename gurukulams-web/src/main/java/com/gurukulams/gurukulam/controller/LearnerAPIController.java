package com.gurukulams.gurukulam.controller;

import com.gurukulams.gurukulam.service.AnswerService;
import com.gurukulams.gurukulam.service.BookService;
import com.gurukulams.gurukulam.service.PracticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Learner api controller.
 */
@RestController
@RequestMapping("/api/learner")
@Tag(name = "Gurukulam Learner", description = "Resource to manage learner")
public class LearnerAPIController {
    /**
     * declare a practiceService.
     */
    private final PracticeService practiceService;

    /**
     * answerService.
     */
    private final AnswerService answerService;

    /**
     * bookService.
     */
    private final BookService bookService;

    /**
     *
     * @param apracticeService
     * @param aAnswerService
     * @param abookService
     */
    public LearnerAPIController(final PracticeService apracticeService,
                                final AnswerService aAnswerService,
                                final BookService abookService) {
        this.practiceService = apracticeService;
        this.answerService = aAnswerService;
        this.bookService = abookService;
    }

    /**
     * Create response entity.
     * @param bookName     the bookName
     * @param request      the request
     * @param createdBy  the createdBy
     * @return the response entity
     */
    @Operation(summary = "Creates a new learner",
            description = "Can be called only by users with"
                    + " 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "question created successfully"),
            @ApiResponse(responseCode = "400",
                    description = "question is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{bookName}/**")
    public ResponseEntity<Object> create(final @PathVariable
                                              String bookName,
                                         final String createdBy,
                                         final HttpServletRequest request)
            throws ServletException, IOException {
        String chapterPath = request.getRequestURI().replaceFirst("/api"
                + "/books/" + bookName
                +  "/", "");


        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookService.learner(bookName,
                        createdBy, chapterPath));
    }
}
