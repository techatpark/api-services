package com.gurukulams.gurukulam.service;

import com.gurukulams.gurukulam.model.Syllabus;
import org.springframework.stereotype.Service;

import java.util.List;
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
     * @param id the id
     * @param userName the userName
     * @param syllabus the syllabus
     * @return question optional
     */
    public Optional<Syllabus> read(final Long id,
                                   final String userName,
                                       final Syllabus syllabus) {
        return null;
    }

    /**
     * update the syllabus.
     * @param id the id
     * @param userName the userName
     * @param syllabus the syllabus
     * @return question optional
     */
    public Optional<Syllabus> update(final Long id,
                                     final String userName,
                                      final Syllabus syllabus) {
        return null;
    }
    /**
     * delete the syllabus.
     * @param id the id
     * @return question optional
     */
    public Boolean delete(final Long id) {
        return false;
    }
    /**
     * list the syllabus.
     * @param userName the userName
     * @param syllabus the syllabus
     * @return question optional
     */
    public List<Syllabus> list(final String userName,
                               final Syllabus syllabus) {
        return null;
    }
}
