package com.gurukulams.gurukulam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.gurukulam.model.Question;
import com.gurukulams.gurukulam.model.QuestionType;
import com.gurukulams.gurukulam.model.UserNote;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Book service.
 */
@Service
public class BookService {

    /**
     * The User note service.
     */
    private final UserNoteService userNotesService;

    /**
     * Service for Practices.
     */
    private final QuestionService questionService;


    /**
     * this helps to practiceService.
     */
    private final PracticeService practiceService;

    /**
     * Instantiates a new Book service.
     *
     * @param theUserNotesService the user note service
     * @param theQuestionService  the question service
     * @param thePracticeService  the practice service
     */
    public BookService(
            final UserNoteService theUserNotesService, final
    QuestionService theQuestionService,
            final PracticeService thePracticeService) {
        this.userNotesService = theUserNotesService;
        this.questionService = theQuestionService;
        this.practiceService = thePracticeService;
    }


    /**
     * Create note optional.
     *
     * @param bookName  the book name
     * @param createdBy     the createdBy
     * @param userNotes the user note
     * @return the optional
     */
    public Optional<UserNote> createNote(final String bookName,
                                         final String createdBy,
                                         final UserNote userNotes) {
        userNotes.setOnType("books");
        userNotes.setOnInstance(bookName);
        return userNotesService.create(userNotes, createdBy);
    }

    /**
     * Search note optional.
     *
     * @param createdBy       the createdBy
     * @param bookName    the book name
     * @param chapterName the chapterName
     * @return the list
     */
    public List<UserNote> searchNotes(final String bookName,
                                      final String createdBy,
                                      final String chapterName) {

        return userNotesService.searchNotes(createdBy, bookName, chapterName);
    }

    /**
     * Read note optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<UserNote> readNote(final Integer id) {
        return userNotesService.read(id);

    }

    /**
     * Update note optional.
     *
     * @param id       the id
     * @param userNote the user note
     * @return the optional
     */
    public Optional<UserNote> updateNote(final Integer id,
                                         final UserNote userNote) {
        return userNotesService.updateNote(id, userNote);
    }

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean delete(final Integer id) {
        return userNotesService.delete(id);
    }

    /**
     * Read note optional.
     *
     * @param userName the username
     * @param bookName the username
     * @param chapterPath the chapterPath
     * @return the optional
     */
    public List<Question> listAllQuestions(final String userName,
                                    final String bookName,
                                           final String chapterPath)
            throws JsonProcessingException {
        return questionService.list(userName, bookName, chapterPath);

    }

    /**
     * Checks if given user is created_by of the book.
     *
     * @param userName
     * @param bookName
     * @return isOwner
     */
    public boolean isOwner(final String userName, final String bookName) {
        return practiceService.isOwner(userName, bookName);
    }
    /**
     * create the question.
     * @param bookName bookName
     * @param questionType the questionType
     * @param question question
     * @param chapterPath chapterPath
     * @return successflag boolean
     */
    public Optional<Question> createAQuestion(final String bookName,
                                              final QuestionType questionType,
                                              final Question question,
                                              final String chapterPath)
            throws JsonProcessingException {


        return questionService.create(bookName, questionType,
                question, chapterPath);
    }

    //create a function to delete, it must done inside question service

    /**
     * delete the question.
     * @param id the id
     * @param questionType the questionType
     * @return successflag boolean
     */
    public Boolean deleteAQuestion(final int id,
                                   final QuestionType questionType) {

        return questionService.deleteAQuestion(id, questionType);
    }

    /**
     * update the question.
     * @param bookName the bookname
     * @param id the id
     * @param questionType the questionType
     * @param question question
     * @param chapterPath the chapterPath
     * @return successflag boolean
     */
    public Optional<Question> updateQuestion(final String bookName,
                                             final Integer id,
                                             final QuestionType questionType,
                                             final Question question,
                                              final String chapterPath)
            throws JsonProcessingException {
        return questionService.update(bookName, questionType, id,
                question, chapterPath);
    }
}
