package com.gurukulams.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * The type Basic architecture test.
 */
public class BasicArchitectureTest {

    @ArchTest
    private final ArchRule no_jodatime = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME;
    JavaClasses classes = new ClassFileImporter().importPackages("com.gurukulams");

    // @ArchTest
    // private final ArchRule no_access_to_standard_streams = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

    // @ArchTest
    // private void no_access_to_standard_streams_as_method(JavaClasses classes) {
    //         noClasses().should(ACCESS_STANDARD_STREAMS).check(classes);
    // }

    // @ArchTest
    // private final ArchRule no_generic_exceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    // @ArchTest
    // private final ArchRule no_java_util_logging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    // @ArchTest
    // private final ArchRule loggers_should_be_private_static_final = fields().that().haveRawType(Logger.class)
    //                 .should().bePrivate().andShould().beStatic().andShould().beFinal()
    //                 .because("we agreed on this convention");

    @Test
    void no_access_to_jdbc() {
        ArchRule no_access_to_jdbc = ArchRuleDefinition.noClasses().that()
                .resideOutsideOfPackage("com.gurukulams.core.service.connector")
                .should().accessClassesThat()
                .belongToAnyOf(Connection.class, Statement.class, PreparedStatement.class)
                .because("we do not use JDBC directly");
        no_access_to_jdbc.check(classes);
    }

    // @ArchTest
    // static final ArchRule no_classes_should_access_standard_streams_or_throw_generic_exceptions = CompositeArchRule
    //                 .of(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS).and(NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS);
}