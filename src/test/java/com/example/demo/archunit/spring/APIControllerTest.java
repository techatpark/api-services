package com.example.demo.archunit.spring;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "com.example.demo")
public class APIControllerTest {
    @ArchTest
    private final ArchRule loggers_should_be_private_static_final = methods().that().areDeclaredInClassesThat()
            .areAnnotatedWith(RestController.class).and().arePublic().should().haveRawReturnType(ResponseEntity.class)
            .because("we don't want to expose domain models directly");

    @ArchTest
    private final ArchRule rule = classes().that().areAnnotatedWith(RestController.class).should().bePackagePrivate()
            .andShould().haveOnlyFinalFields().because("Controllers should be only delegates");

}