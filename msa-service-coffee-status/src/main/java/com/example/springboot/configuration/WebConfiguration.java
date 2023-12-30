package com.example.springboot.configuration;

import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    static final String h2DcWebConsoleContext = "/console/*";

    @Bean
    ServletRegistrationBean h2servletRegistration(){

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new JakartaWebServlet());
        servletRegistrationBean.addUrlMappings(h2DcWebConsoleContext);
        return servletRegistrationBean;
    }
}
