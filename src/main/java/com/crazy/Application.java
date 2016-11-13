package com.crazy;

import org.springframework.boot.SpringApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Created by SHIKUN on 2016/9/9.
 */

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {


    }


}


