package com.gurukulams.core.service;

import com.gurukulams.core.model.Institute;
import com.gurukulams.core.model.Syllabus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Institute service.
 */
@Service
public class InstituteService {

    /**
     * inserts data.
     * @param userName the userName
     * @param institute the institute
     * @return question optional
     */
    public Optional<Institute> create(final String userName,
                                      final Institute institute) {
        return null;
    }

    /**
     * reads from institute.
     * @param id the id
     * @param userName the userName
     * @param institute the institute
     * @return question optional
     */
    public Optional<Institute> read(final Long id,
                                   final String userName,
                                   final Institute institute) {
        return null;
    }

    /**
     * update the institute.
     * @param id the id
     * @param userName the userName
     * @param institute the institute
     * @return question optional
     */
    public Optional<Institute> update(final Long id,
                                     final String userName,
                                     final Institute institute) {
        return null;
    }

    /**
     * delete the institute.
     * @param id the id
     */
    public Boolean delete(final Long id) {
        return false;
    }

    /**
     * list the institute.
     * @param userName the userName
     * @param institute the institute
     * @return question optional
     */
    public List<Institute> list(final String userName,
                               final Institute institute) {
        return null;
    }
}
