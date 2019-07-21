package com.yyb.springannonation.controller;

import com.yyb.springannonation.config.JavaConfig;
import com.yyb.springannonation.model.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        Person person = annotationConfigApplicationContext.getBean(Person.class);
        person.networking();
    }
}
