package com.gurukulams.gurukulam.service;

import com.gurukulams.gurukulam.model.Syllabus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Syllabus service.
 */
@Service
public class SyllabusService {

    /**
     * inserts data.
     * @param userName the userName
     * @param syllabus the syllabus
     * @return question optional
     */
    public Optional<Syllabus> create(final String userName,
                                     final Syllabus syllabus) {
        return null;
    }
    /**
     * reads from syllabus.
     * @param userName the userName
     * @param syllabus the syllabus
     * @return question optional
     */
    public Optional<Syllabus> read(final String userName,
                                   final Syllabus syllabus) {
        return null;
    }

    /**
     * update the syllabus.
     * @param userName the userName
     * @param syllabus the syllabus
     * @return question optional
     */
    public Optional<Syllabus> update(final String userName,
                                     final Syllabus syllabus) {
        return null;
    }

    /**
     * deletes from database.
     * @param userName the userName
     * @param syllabus the syllabus
     * @return question optional
     */
    public Boolean delete(final String userName,
                                     final Syllabus syllabus) {
        return null;
    }
}
