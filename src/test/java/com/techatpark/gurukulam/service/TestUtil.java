package com.techatpark.gurukulam.service;

import com.techatpark.gurukulam.model.sql.SqlPractice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class TestUtil {
    /**
     * Create Temporary SQL Files in temp folder. Return Files as array.
     *
     * @param exam
     * @return array of sript file
     */
    public static String getScript(final SqlPractice exam) {

        final File file = new File(
                "src/test/resources/scripts/" + exam.getDatabase().getValue() +
                        "/1.sql");
        try {
            return Files.readString(file.toPath());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
