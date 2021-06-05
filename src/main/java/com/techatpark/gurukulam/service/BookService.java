package com.techatpark.gurukulam.service;

import com.techatpark.gurukulam.model.UserNote;
import org.springframework.stereotype.Service;

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
     * Instantiates a new Book service.
     *
     * @param theUserNotesService the user note service
     */
    public BookService(
            final UserNoteService theUserNotesService) {
        this.userNotesService = theUserNotesService;
    }


    /**
     * Create note optional.
     *
     * @param bookName  the book name
     * @param userNotes the user note
     * @return the optional
     */
    public Optional<UserNote> createNote(final String bookName,
                                     final UserNote userNotes) {
        userNotes.setOnType("books");
        userNotes.setOnInstance(bookName);
        return  userNotesService.create(userNotes);
    }

    /**
     * Read note optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<UserNote> readNote(final Integer id) {
        return  userNotesService.read(id);

    }
}
