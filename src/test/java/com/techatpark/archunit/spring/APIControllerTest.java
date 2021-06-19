package com.techatpark.archunit.spring;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import com.tngtech.archunit.lang.syntax.elements.GivenMethodsConjunction;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

/**
 * The type Api controller test.
 */
public class APIControllerTest {

    private static final GivenClassesConjunction CONTROLLER_CLASSES = classes()
            .that()
            .areAnnotatedWith(RestController.class);

    private static final GivenMethodsConjunction CONTROLLER_PUBLIC_METHODS
            = methods().that().areDeclaredInClassesThat()
            .areAnnotatedWith(RestController.class).and().arePublic();

    @Test
    public void controller_immutable_stateless() {
        JavaClasses importedClasses = new ClassFileImporter()
                .importPackages("com.techatpark");

        ArchRule rule = CONTROLLER_CLASSES
                .should()
                .bePackagePrivate()
                .andShould().haveOnlyFinalFields()
                .andShould().accessClassesThat()
                .resideOutsideOfPackage("javax.validation")
                .andShould().onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "com.techatpark.gurukulam.controller"
                        , "java.net"
                        , "com.techatpark.starter.security.security"
                        , "org.springframework.security.authentication"
                        , "org.springframework.beans.factory.annotation"
                        , "java.util"
                        , "..service.."
                        ,"..model.."
                        , "..payload.."
                        , "java.lang"
                , "org.springframework.http"
                        , "org.springframework.web.bind.annotation"
                , "io.swagger.v3.oas.annotations"
                        , "io.swagger.v3.oas.annotations.tags"
                        , "io.swagger.v3.oas.annotations.parameters"
                        , "org.springframework.web.bind"
                        , "io.swagger.v3.oas.annotations.responses"
                        , "io.swagger.v3.oas.annotations.security"
                , "java.security")
                .because("Controllers should be only delegates");

        rule.check(importedClasses);

        rule = CONTROLLER_PUBLIC_METHODS
                .should()
                .haveRawReturnType(ResponseEntity.class)
                .because("we don't want to expose domain models directly");

        rule.check(importedClasses);
    }


}
