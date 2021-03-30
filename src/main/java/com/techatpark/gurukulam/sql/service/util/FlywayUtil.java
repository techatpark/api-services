package com.techatpark.gurukulam.sql.service.util;

import com.techatpark.gurukulam.sql.model.Exam;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class FlywayUtil {

    private FlywayUtil() {

    }

    /**
     * Load scripts to db.
     *
     * @param exam
     * @param dataSource
     * @return successFlag
     */
    public static Boolean loadScripts(final Exam exam, final DataSource dataSource) {
        if (exam.getScript() != null) {
            // Load Script files in temp folder
            try {
                Path createdTempFolder = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")),
                        new Date().getTime() + "Exams");

                Files.writeString(
                        createdTempFolder.resolve(Paths.get("V1__script.sql"))
                        , exam.getScript());

                Map<String, String> flywayConfig = new HashMap<>(1);
                flywayConfig.put("flyway.locations", createdTempFolder.toFile().getAbsolutePath());
                final Flyway flyway = Flyway.configure().schemas("EXAM_" + exam.getId()).dataSource(dataSource)
                        .configuration(flywayConfig).load();
                flyway.migrate();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return true;
    }
}
