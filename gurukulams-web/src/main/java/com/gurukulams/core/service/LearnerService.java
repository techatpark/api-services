package com.gurukulams.core.service;

import com.gurukulams.core.model.Learner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerService {

    /**
     *
     * @param userName
     * @param learner
     * @return learner
     */
    public Learner create(final String userName,
                          final Learner learner) {
        return null;
    }

    /**
     *
     * @param userName
     * @param id
     * @return learner
     */
    public Optional<Learner> read(final String userName,
                                 final Long id) {
        return null;
    }

    /**
     *
     * @param id
     * @param userName
     * @param learner
     * @return learner
     */
    public Learner update(final Long id,
                          final String userName,
                          final Learner learner) {
        return null;
    }

    /**
     *
     * @param id
     * @param userName the userName
     * @return learner
     */
    public Boolean delete(final String userName,
                          final Long id) {
        return false;
    }

    /**
     *
     * @param userName
     * @return learner
     */
    public List<Learner> list(final String userName) {
        return null;
    }
}
