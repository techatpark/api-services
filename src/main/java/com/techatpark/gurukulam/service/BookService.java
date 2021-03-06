package com.techatpark.gurukulam.service;

import com.techatpark.gurukulam.model.UserNote;
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
     * @param owner     the owner
     * @param userNotes the user note
     * @return the optional
     */
    public Optional<UserNote> createNote(final String bookName,
                                         final String owner,
                                         final UserNote userNotes) {
        userNotes.setOnType("books");
        userNotes.setOnInstance(bookName);
        return userNotesService.create(userNotes, owner);
    }

    /**
     * Search note optional.
     *
     * @param owner       the owner
     * @param bookName    the book name
     * @param chapterName the chapterName
     * @return the list
     */
    public List<UserNote> searchNotes(final String bookName,
                                      final String owner,
                                      final String chapterName) {

        return userNotesService.searchNotes(owner, bookName, chapterName);
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
}
