package com.gurukulams.service;

import com.gurukulams.core.model.Category;
import com.gurukulams.core.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@SpringBootTest
class TagServiceTest {

    @Autowired
    private CategoryService tagService;

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
        tagService.deleteAll();
    }


    @Test
    void create() {
        final Category tag = tagService.create("hari"
                , null, anTag());
        Assertions.assertTrue(tagService.read("hari", tag.id(), null).isPresent(), "Created Category");
    }

    @Test
    void createLocalized() {
        final Category tag = tagService.create("hari"
                , Locale.GERMAN, anTag());
        Assertions.assertTrue(tagService.read("hari", tag.id(), Locale.GERMAN).isPresent(), "Created Localized Category");
        Assertions.assertTrue(tagService.read("hari", tag.id(), null).isPresent(), "Created Category");
    }

    @Test
    void read() {
        final Category tag = tagService.create("hari",
                null, anTag());
        Assertions.assertTrue(tagService.read("hari", tag.id(), null).isPresent(),
                "Created Category");
    }

    @Test
    void update() {

        final Category tag = tagService.create("hari",
                null, anTag());
        Category newTag = new Category(tag.id(), "HansiTag", null, null, null, null);
        Category updatedTag = tagService
                .update(tag.id(), "priya", null, newTag);
        Assertions.assertEquals("HansiTag", updatedTag.title(), "Updated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tagService
                    .update(UUID.randomUUID().toString(), "priya", null, newTag);
        });
    }

    @Test
    void updateLocalized() {

        final Category tag = tagService.create("hari",
                null, anTag());
        Category newTag = new Category(tag.id(), "HansiTag", null, null, null, null);
        Category updatedTag = tagService
                .update(tag.id(), "priya", Locale.GERMAN, newTag);

        Assertions.assertEquals("HansiTag", tagService.read("mani", tag.id(), Locale.GERMAN).get().title(), "Updated");
        Assertions.assertNotEquals("HansiTag", tagService.read("mani", tag.id(), null).get().title(), "Updated");


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tagService
                    .update(UUID.randomUUID().toString(), "priya", null, newTag);
        });
    }

    @Test
    void delete() {

        final Category tag = tagService.create("hari", null,
                anTag());
        tagService.delete("mani", tag.id());
        Assertions.assertFalse(tagService.read("mani", tag.id(), null).isPresent(), "Deleted Category");
    }

    @Test
    void list() {

        final Category tag = tagService.create("hari", null,
                anTag());
        Category newTag = new Category(UUID.randomUUID().toString(), "HansiTag", null, null, null, null);
        tagService.create("hari", null,
                newTag);
        List<Category> listofcategories = tagService.list("hari", null);
        Assertions.assertEquals(2, listofcategories.size());

    }

    @Test
    void listLocalized() {

        final Category tag = tagService.create("hari", Locale.GERMAN,
                anTag());
        Category newTag = new Category(UUID.randomUUID().toString(), "HansiTag", null, null, null, null);
        tagService.create("hari", null,
                newTag);
        List<Category> listofcategories = tagService.list("hari", null);
        Assertions.assertEquals(2, listofcategories.size());

        listofcategories = tagService.list("hari", Locale.GERMAN);
        Assertions.assertEquals(2, listofcategories.size());

    }


    /**
     * Gets practice.
     *
     * @return the practice
     */
    Category anTag() {

        Category tag = new Category(UUID.randomUUID().toString(), "HariTag", null, null, null, null);
        return tag;
    }


}