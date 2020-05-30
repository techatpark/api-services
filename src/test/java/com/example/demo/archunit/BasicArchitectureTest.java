package com.example.demo.archunit;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTag;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@ArchTag("basic")
@AnalyzeClasses(packages = "com.example.demo")
public class BasicArchitectureTest {

        // @ArchTest
        // private final ArchRule no_access_to_jdbc = noClasses().should().accessClassesThat()
        //                 .belongToAnyOf(Connection.class, Statement.class, PreparedStatement.class)
        //                 .because("we do not use JDBC directly");

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

        @ArchTest
        private final ArchRule no_jodatime = NO_CLASSES_SHOULD_USE_JODATIME;

        // @ArchTest
        // static final ArchRule no_classes_should_access_standard_streams_or_throw_generic_exceptions = CompositeArchRule
        //                 .of(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS).and(NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS);
}