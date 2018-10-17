package com.alltej.eventmngmt.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author atejano
 */
//@Configuration
//@ComponentScan(basePackages = {"com.alltej.eventmngmt"})
public class TestWebConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}