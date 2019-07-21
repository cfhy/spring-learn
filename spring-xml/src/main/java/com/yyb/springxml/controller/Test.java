package com.yyb.springxml.controller;

import com.yyb.springxml.model.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        Person person = applicationContext.getBean(Person.class);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        person.networking();
    }
}
