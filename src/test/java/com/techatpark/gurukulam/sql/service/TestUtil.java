package com.techatpark.gurukulam.sql.service;

import com.techatpark.gurukulam.sql.model.Exam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class TestUtil {
    /**
     * Create Temporary SQL Files in temp folder. Return Files as array.
     *
     * @param exam
     * @return array of sript file
     */
    public static InputStream[] getScriptFiles(final Exam exam) {
        InputStream[] scripts = new InputStream[2];
        File file = new File("src/test/resources/" + exam.getDatabase().getValue() + "/scripts");
        return Arrays.asList(file.listFiles()).stream().map(script -> {
            try {
                return new FileInputStream(script);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList())
                .toArray(scripts);
    }
}
