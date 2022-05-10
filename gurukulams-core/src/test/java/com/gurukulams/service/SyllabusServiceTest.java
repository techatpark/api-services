package com.gurukulams.service;

import com.gurukulams.core.model.Board;
import com.gurukulams.core.model.Grade;
import com.gurukulams.core.model.Syllabus;
import com.gurukulams.core.service.BoardService;
import com.gurukulams.core.service.GradeService;
import com.gurukulams.core.service.SyllabusService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SyllabusServiceTest {

    public static final String STATE_SYLLABUS_IN_ENGLISH = "State Board";
    public static final String STATE_SYLLABUS_DESCRIPTION_IN_ENGLISH = "State Board Description";
    public static final String STATE_SYLLABUS_TITLE_IN_FRENCH = "Conseil d'État";
    public static final String STATE_SYLLABUS_DESCRIPTION_IN_FRENCH = "Description du conseil d'État";

    @Autowired
    private SyllabusService syllabusService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private GradeService gradeService;



    /**
     * Before.
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void before() throws IOException {
        cleanUp();
    }

    /**
     * After.
     */
    @AfterEach
    void after() {
        cleanUp();
    }

    private void cleanUp() {
        syllabusService.deleteAll();
    }

    @Test
    void create(){
        final Syllabus syllabus = syllabusService.create("mani", null,
                anSyllabus());
        Assertions.assertTrue(syllabusService.read("mani", null,
                syllabus.id()).isPresent(),"Created Syllobous");
    }

    @Test
    void read() {
        final Syllabus syllabus=syllabusService.create("mani", null,
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Assertions.assertTrue(syllabusService.read("mani", null,
                        newSyllabusId).isPresent(),
                "syllabus Created");
    }

    @Test
    void update() {

        final Syllabus syllabus = syllabusService.create("mani", null,
                anSyllabus());
        final Long newSyllabusId = syllabus.id();
        Syllabus newSyllabus = new Syllabus(null, "MathsSyllabus", "An " +
                "Syllabus", null, "tom", null, null);
        Syllabus updatedSyllabus = syllabusService
                .update(newSyllabusId, "manikanta", null, newSyllabus);
        Assertions.assertEquals("MathsSyllabus", updatedSyllabus.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            syllabusService
                    .update(10000L, "manikanta", null, newSyllabus);
        });
    }

    @Test
    void delete() {

        final Syllabus syllabus = syllabusService.create("mani", null,
                anSyllabus());
        syllabusService.delete("mani",syllabus.id());
        Assertions.assertFalse(syllabusService.read("mani", null,
                              syllabus.id()).isPresent(),"Deleted Syllobous");

    }

    @Test
    void list() {

        final Syllabus syllabus = syllabusService.create("manikanta", null,
                anSyllabus());
        Syllabus newSyllabus = new Syllabus(null, "PhysicsSyllabus", "An " +
                "Syllabus", null, "tom", null, null);
        syllabusService.create("manikanta", null,
                newSyllabus);
        List<Syllabus> listofsyllabus = syllabusService.list("manikanta", null);
        Assertions.assertEquals(2, listofsyllabus.size());

    }

    @Test
    void testLocalizationFromDefaultWithoutLocale() {
        // Create a Syllabus for Default Language
        final Syllabus syllabus = syllabusService.create("mani",null,
                anSyllabus());

        testLocalization(syllabus);

    }

    @Test
    void testLocalizationFromCreateWithLocale() {
        // Create a Syllabus for GERMAN Language
        final Syllabus syllabus = syllabusService.create("mani",Locale.GERMAN,
                anSyllabus());

        testLocalization(syllabus);

    }

    private void testLocalization(Syllabus syllabus) {
        // Update for China Language
        syllabusService.update(syllabus.id(),"mani", Locale.FRENCH,anSyllabus(syllabus,
                STATE_SYLLABUS_TITLE_IN_FRENCH,
                STATE_SYLLABUS_DESCRIPTION_IN_FRENCH));

        // Get for french Language
        Syllabus createSyllabus = syllabusService.read("mani",Locale.FRENCH,
                syllabus.id()).get();
        Assertions.assertEquals(STATE_SYLLABUS_TITLE_IN_FRENCH, createSyllabus.title());
        Assertions.assertEquals(STATE_SYLLABUS_DESCRIPTION_IN_FRENCH, createSyllabus.description());

        final Long id = createSyllabus.id();
        createSyllabus = syllabusService.list("mani", Locale.FRENCH)
                .stream()
                .filter(syllabus1 -> syllabus1.id().equals(id))
                .findFirst().get();
        Assertions.assertEquals(STATE_SYLLABUS_TITLE_IN_FRENCH, createSyllabus.title());
        Assertions.assertEquals(STATE_SYLLABUS_DESCRIPTION_IN_FRENCH,
                createSyllabus.description());

        // Get for France which does not have data
        createSyllabus = syllabusService.read("mani",Locale.CHINESE,
                syllabus.id()).get();
        Assertions.assertEquals(STATE_SYLLABUS_IN_ENGLISH, createSyllabus.title());
        Assertions.assertEquals(STATE_SYLLABUS_DESCRIPTION_IN_ENGLISH, createSyllabus.description());

        createSyllabus = syllabusService.list("mani",Locale.CHINESE)
                .stream()
                .filter(syllabus1 -> syllabus1.id().equals(id))
                .findFirst().get();

        Assertions.assertEquals(STATE_SYLLABUS_IN_ENGLISH, createSyllabus.title());
        Assertions.assertEquals(STATE_SYLLABUS_DESCRIPTION_IN_ENGLISH, createSyllabus.description());
    }


    /**
     * Gets syllabus.
     *
     * @return the syllabus
     */
    Syllabus anSyllabus() {

        Syllabus syllabus = new Syllabus(null, STATE_SYLLABUS_IN_ENGLISH,
                STATE_SYLLABUS_DESCRIPTION_IN_ENGLISH, null, null,
                null, null);
        return syllabus;
    }

    /**
     * Gets syllabus from reference syllabus.
     *
     * @return the syllabus
     */
    Syllabus anSyllabus(final Syllabus ref,final String title,final String description) {
        return new Syllabus(ref.id(), title,
                description, ref.created_at(), ref.created_by(),
                ref.modified_at(), ref.modified_by());
    }


    Grade aGrade() {

        Grade grade = new Grade(null, "Student Grade",
                "A " + "Grade", null, null,
                null, null);
        return grade;
    }


    /**
     * Gets board.
     *
     * @return the board
     */
    Board anBoard() {

        Board board = new Board(null, "State Board",
                "A " + "Board", null, null,
                null, null);
        return board;
    }
}
