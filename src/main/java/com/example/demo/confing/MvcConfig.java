package com.example.demo.confing;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index.html");
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/personList").setViewName("personList.html");
        registry.addViewController("/addPerson").setViewName("addPerson.html");
        registry.addViewController("/addRole").setViewName("addRole.html");
        registry.addViewController("/addRolePers").setViewName("addRolePers.html");
        registry.addViewController("/delPerson").setViewName("delPerson.html");
        registry.addViewController("/delrolePerson").setViewName("delrolePerson.html");
        registry.addViewController("/rolPerson").setViewName("rolPerson.html");
        registry.addViewController("/profilePer/{id}").setViewName("profilePer/{id}.html");
    }
}