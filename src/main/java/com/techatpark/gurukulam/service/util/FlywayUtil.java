package com.techatpark.gurukulam.service.util;

import com.techatpark.gurukulam.model.sql.SqlPractice;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Flyway util.
 */
public final class FlywayUtil {

    private FlywayUtil() {

    }

    /**
     * Load scripts to db.
     *
     * @param exam       the exam
     * @param dataSource the data source
     * @return successFlag boolean
     */
    public static Boolean loadScripts(final SqlPractice exam,
                                      final DataSource dataSource) {
        if (exam.getScript() != null) {
            // Load Script files in temp folder
            try {
                final Path createdTempFolder = Files.createTempDirectory(
                        Paths.get(System.getProperty("java.io.tmpdir")),
                        new Date().getTime() + "Exams");

                Files.writeString(
                        createdTempFolder.resolve(Paths.get("V1__script.sql")),
                        exam.getScript());

                final Map<String, String> flywayConfig = new HashMap<>(1);
                flywayConfig.put("flyway.locations", "filesystem:"
                        + createdTempFolder.toFile().getAbsolutePath());


                final Flyway flyway = Flyway.configure()
                        .defaultSchema("EXAM_" + exam.getId())
                        .schemas("EXAM_" + exam.getId())
                        .createSchemas(true)
                        .dataSource(dataSource)
                        .configuration(flywayConfig).load();
                flyway.migrate();
            } catch (final IOException e) {
                e.printStackTrace();
            }

        }

        return true;
    }
}
