package com.gurukulams.starter.swagger;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import java.util.Iterator;

/**
 * The type Swagger config.
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Gurukulam API",
        version = "2.0",
        description = "Gurukulam API Documentation",
        license = @License(name = "Apache 2.0",
                url = "https://foo.bar"),
        contact = @Contact(url = "https://www.techatpark.com",
                name = "TECHATPARK")
), servers = @Server(url = "/", description = "Local"))
@SecurityScheme(
        name = "bearerAuth",
        description = "Token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig implements ModelConverter {
    /**
     * @param type
     * @param context
     * @param chain
     * @return schema
     */
    @Override
    public Schema resolve(final AnnotatedType type,
                          final ModelConverterContext context,
                          final Iterator<ModelConverter> chain) {
        final JavaType javaType = Json.mapper().constructType(type.getType());
        AnnotatedType typeToChain = type;
        if (javaType != null) {
            final Class<?> cls = javaType.getRawClass();
            if (Page.class.isAssignableFrom(cls)) {
                final JavaType innerType = javaType.getBindings()
                        .getBoundType(0);
                if (innerType.getBindings() != null) {
                    return this.resolve(new AnnotatedType(innerType)
                            .jsonViewAnnotation(type.getJsonViewAnnotation())
                            .resolveAsRef(true), context, chain);
                } else {
                    typeToChain = new AnnotatedType(innerType)
                            .jsonViewAnnotation(type.getJsonViewAnnotation())
                            .resolveAsRef(true);
                }
            }
        }
        if (chain.hasNext()) {
            return chain.next().resolve(typeToChain, context, chain);
        } else {
            return null;
        }
    }

}
