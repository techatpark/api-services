package com.gurukulams.web.api;

import com.gurukulams.core.model.Event;
import com.gurukulams.core.service.EventService;
import com.gurukulams.core.service.BookService;
import com.gurukulams.core.service.GradeService;
import com.gurukulams.core.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "Resource to manage Event")
class EventAPIController {

    /**
     * declare a event service.
     */
    private final EventService eventService;

    /**
     * declare a grade service.
     */
    private final GradeService gradeService;

    /**
     * declare a syllabus service.
     */
    private final SubjectService subjectService;

    /**
     * declare a bookservice.
     */
    private final BookService bookService;

    EventAPIController(final EventService aEventService,
                       final GradeService agradeService,
                       final SubjectService asubjectService,
                       final BookService aBookService) {
        this.eventService = aEventService;
        this.gradeService = agradeService;
        this.subjectService = asubjectService;
        this.bookService = aBookService;
    }

    /**
     * Create response entity.
     *
     * @param principal the principal
     * @param event     the event name
     * @param locale    the locale
     * @return the response entity
     */
    @Operation(summary = "Creates a new event",
            description = "Can be called "
                    + "only by users with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "201",
            description = "event created successfully"),
            @ApiResponse(responseCode = "400",
                    description = "event is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Event> create(final Principal principal,
                                @RequestHeader(name = "Accept-Language",
                                        required = false) final Locale locale,
                                        @RequestBody final Event event) {
        Event created = eventService.create(principal.getName(), locale, event);
        return ResponseEntity.created(URI.create("/api/event" + created.id()))
                .body(created);
    }

    /**
     * Read a event.
     *
     * @param id
     * @param principal
     * @param locale    the locale
     * @return a event
     */
    @Operation(summary = "Get the Event with given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "getting event successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})

    @GetMapping("/{id}")
    public ResponseEntity<Event> read(@PathVariable final UUID id,
                                      @RequestHeader(name = "Accept-Language",
                                          required = false) final Locale locale,
                                      final Principal principal) {
        return ResponseEntity.of(eventService.read(principal.getName(),
                locale, id));
    }

    /**
     * Update a Event.
     *
     * @param id
     * @param principal
     * @param locale
     * @param event
     * @return a event
     */
    @Operation(summary = "Updates the event by given id",
            description = "Can be called only by users "
                    + "with 'auth management' rights.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "event updated successfully"),
            @ApiResponse(responseCode = "400",
                    description = "event is invalid"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "syllabus not found")})
    @PutMapping(value = "/{id}", produces = "application/json", consumes =
            "application/json")
    public ResponseEntity<Event> update(@PathVariable final UUID id,
                                        final Principal
                                                principal,
                                        @RequestHeader(name = "Accept-Language",
                                        required = false) final Locale locale,
                                        @RequestBody final Event
                                                event) {
        final Event updatedEvent =
                eventService.update(id, principal.getName(), locale, event);
        return updatedEvent == null ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updatedEvent);
    }

    /**
     * Delete a Event.
     *
     * @param id
     * @param principal
     * @return event
     */
    @Operation(summary = "Deletes the event by given id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "event deleted successfully"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials"),
            @ApiResponse(responseCode = "404",
                    description = "event not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final
                                       UUID id,
                                       final Principal principal) {
        return eventService.delete(principal.getName(),
                id) ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * List the Events.
     *
     * @param principal
     * @param locale
     * @return list of event
     */
    @Operation(summary = "lists the event",
            description = " Can be invoked by auth users only",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Listing the event"),
            @ApiResponse(responseCode = "204",
                    description = "event are not available"),
            @ApiResponse(responseCode = "401",
                    description = "invalid credentials")})
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Event>> list(final Principal
                                                    principal,
                                        @RequestHeader(name = "Accept-Language",
                                        required = false) final Locale locale) {
        final List<Event> eventList = eventService.list(
                principal.getName(), locale);
        return eventList.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(eventList);
    }


}
