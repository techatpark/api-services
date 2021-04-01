package com.techatpark.gurukulam.sql.service;

import com.techatpark.gurukulam.sql.model.Practice;

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
    public static String getScript(final Practice exam) {

        File file = new File("src/test/resources/scripts/"+ exam.getDatabase().getValue() +"/1.sql");
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
