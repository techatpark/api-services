package com.gurukulams.gurukulam.controller;

import com.gurukulams.gurukulam.model.Syllabus;
import com.gurukulams.gurukulam.service.SyllabusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * The type Syllabus api controller.
 */
@RestController
@RequestMapping("/api/syllabus")
@Tag(name = "Syllabus", description = "Resource to manage Syllabus")
class SyllabusAPIController {
    /**
     * declare a syllabusService.
     */
    private final SyllabusService syllabusService;

    SyllabusAPIController(final SyllabusService asyllabusService) {
        this.syllabusService = asyllabusService;
    }


    /**
     * inserts data.
     * @param principal the userName
     * @param syllabus the syllabus
     * @return question optional
     */
    public ResponseEntity<Syllabus> create(final Principal principal,
                                final @RequestBody Syllabus syllabus) {
        return syllabusService.create(principal.getName(), syllabus);
    }

    /**
     * reads from syllabus.
     * @param principal the principal
     * @param syllabus the syllabus
     * @return question optional
     */
    public ResponseEntity<Syllabus> read(final Principal principal,
                                   final @RequestBody Syllabus syllabus) {
        return syllabusService.read(principal.getName(), syllabus);
    }

    /**
     * update the syllabus.
     * @param principal the principal
     * @param syllabus the syllabus
     * @return syllabus optional
     */
    public ResponseEntity<Syllabus> update(final Principal principal,
                               final @RequestBody Syllabus syllabus) {
        return syllabusService.update(principal.getName(), syllabus);
    }

    /**
     * delete the syllabus.
     * @param id the id
     * @return question optional
     */
    public Boolean delete(final long id) {
        return syllabusService.delete(id);
    }

    /**
     * list the syllabus.
     * @param principal the principal
     * @param syllabus the syllabus
     * @return question optional
     */
    public ResponseEntity<Syllabus> list(final Principal principal,
                            final @RequestBody Syllabus syllabus) {
        return syllabusService.list(principal.getName(), syllabus);
    }
}


