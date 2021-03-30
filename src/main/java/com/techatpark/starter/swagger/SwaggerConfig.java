package com.techatpark.starter.swagger;

import com.fasterxml.jackson.databind.JavaType;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import java.util.Iterator;

@Configuration
@OpenAPIDefinition(info = @Info(title = "My API",
        version = "0.0",
        description = "My API description",
        license = @License(name = "Apache 2.0",
                url = "https://foo.bar"),
        contact = @Contact(url = "https://www.gurukulam.techatpark.com",
                name = "Fred",
                email = "Fred@gigagantic-server.com")
))
public class SwaggerConfig implements ModelConverter {
    @Override
    public Schema resolve(AnnotatedType type, final ModelConverterContext context,
                          final Iterator<ModelConverter> chain) {
        final JavaType javaType = Json.mapper().constructType(type.getType());
        if (javaType != null) {
            final Class<?> cls = javaType.getRawClass();
            if (Page.class.isAssignableFrom(cls)) {
                final JavaType innerType = javaType.getBindings().getBoundType(0);
                if (innerType.getBindings() != null) {
                    type = new AnnotatedType(innerType)
                            .jsonViewAnnotation(type.getJsonViewAnnotation())
                            .resolveAsRef(true);
                    return this.resolve(type, context, chain);
                } else {
                    type = new AnnotatedType(innerType)
                            .jsonViewAnnotation(type.getJsonViewAnnotation())
                            .resolveAsRef(true);
                }
            }
        }
        if (chain.hasNext()) {
            return chain.next().resolve(type, context, chain);
        } else {
            return null;
        }
    }

}
