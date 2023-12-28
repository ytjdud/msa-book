package com.example.springboot.configuration;

import jakarta.servlet.Servlet;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    static final String h2DcWebConsoleContext = "/console/*";

    @Bean
    ServletRegistrationBean h2servletRegistration(){

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean((Servlet) new WebServlet());
        servletRegistrationBean.addUrlMappings(h2DcWebConsoleContext);
        return servletRegistrationBean;
    }
}