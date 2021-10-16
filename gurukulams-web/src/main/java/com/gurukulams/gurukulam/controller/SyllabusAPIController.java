package com.gurukulams.gurukulam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gurukulams.gurukulam.model.Syllabus;
import com.gurukulams.gurukulam.service.SyllabusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
     * @return syllabus optional
     */
    public ResponseEntity<Optional<Syllabus>> create(final Principal principal,
                                        final @RequestBody Syllabus syllabus) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                syllabusService.create(principal.getName(), syllabus));
    }

    /**
     * reads from syllabus.
     * @param principal the principal
     * @param syllabus the syllabus
     * @return syllabus optional
     */
    public ResponseEntity<Syllabus> read(final Principal principal,
                                   final @RequestBody Syllabus syllabus) {
        return ResponseEntity.of(syllabusService.read(principal.getName(),
                                                                syllabus));
    }

    public ResponseEntity<Optional<Syllabus>> update(final Principal
                                                        principal,
                                                     final @RequestBody
                                                             Syllabus
                                                             syllabus)
            throws JsonProcessingException {
        final Optional<Syllabus> updatedSyllabus =
                syllabusService.update(principal.getName(), syllabus);
        return updatedSyllabus == null ? new ResponseEntity<>(
                HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(updatedSyllabus);
    }

    /**
     * delete the syllabus.
     * @param id the id
     * @return syllabus optional
     */
    public ResponseEntity<Void> delete(final @PathVariable
                                               long id) {
        return syllabusService.delete(id) ? ResponseEntity.ok().build()
                : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    /**
     * list the syllabus.
     * @param principal the principal
     * @param syllabus the syllabus
     * @return syllabus optional
     */
    public ResponseEntity<List<Syllabus>> list(final Principal
                                                     principal,
                                                           final
                                                           @RequestBody
                                                                   Syllabus
                                                                   syllabus) {
        final List<Syllabus> syllabusList = syllabusService.list(
                principal.getName(),
                syllabus);
        return syllabusList.isEmpty() ? new ResponseEntity<List<Syllabus>>(
                HttpStatus.NO_CONTENT)
                : ResponseEntity.ok(syllabusList);
    }
}


