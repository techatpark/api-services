package com.techatpark.gurukulam.sql.service.util;

import com.techatpark.gurukulam.sql.model.Exam;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class FlywayUtil {

    private FlywayUtil() {

    }

    /**
     * Load scripts to db.
     * 
     * 
     * @param exam
     * @param scriptFiles
     * @param dataSource
     * @return successFlag
     */
    public static Boolean loadScripts(final Exam exam, final InputStream[] scriptFiles, final DataSource dataSource) {
        if (scriptFiles != null) {
            // Load Script files in temp folder
            try {
                Path createdTempFolder = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")),
                        new Date().getTime() + "Exams");
                for (int i = 0; i < scriptFiles.length; i++) {
                    Files.copy(scriptFiles[i], createdTempFolder.resolve(Paths.get("V" + (i + 1) + "__script.sql")),
                            StandardCopyOption.REPLACE_EXISTING);
                }

                Map<String, String> flywayConfig = new HashMap<>(1);
                flywayConfig.put("flyway.locations", createdTempFolder.toFile().getAbsolutePath());
                final Flyway flyway = Flyway.configure().schemas("EXAM_" + exam.getId()).dataSource(dataSource)
                        .configuration(flywayConfig).load();
                flyway.migrate();
            } catch (IOException e) {
                
            }

        }

        return true;
    }
}
