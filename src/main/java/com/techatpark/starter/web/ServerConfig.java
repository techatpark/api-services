package com.techatpark.starter.web;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * Service configurations.
 */
//@Configuration
public class ServerConfig {

    /**
     * server port.
     */
    @Value("${http.port}")
    private Integer httpPort;

    /**
     * server's https port.
     */
    @Value("${server.port}")
    private Integer httpsPort;

    /**
     * method to get servlet containers.
     *
     * @return ServletWebServerFactory servlet web server factory
     */
    @Bean
    public ServletWebServerFactory servletContainer() {
        final TomcatServletWebServerFactory tomcat = new
                TomcatServletWebServerFactory() {
                    @Override
                    protected void postProcessContext(final Context context) {
                        final SecurityConstraint securityConstraint = new
                                SecurityConstraint();
                        securityConstraint.setUserConstraint("CONFIDENTIAL");
                        final SecurityCollection collection
                                = new SecurityCollection();
                        collection.addPattern("/*");
                        securityConstraint.addCollection(collection);
                        context.addConstraint(securityConstraint);
                    }
                };
        tomcat.addAdditionalTomcatConnectors(getHttpConnector());
        return tomcat;
    }

    private Connector getHttpConnector() {
        final Connector connector = new Connector(TomcatServletWebServerFactory
                .DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(httpPort);
        connector.setSecure(false);
        connector.setRedirectPort(httpsPort);
        return connector;
    }
}
