package com.yyb.springxml.controller;

import com.yyb.springxml.model.Computer;
import com.yyb.springxml.model.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        Person person = applicationContext.getBean(Person.class);
       System.out.println(person.getName());

        Computer computer = applicationContext.getBean(Computer.class);
        System.out.println(computer.getName());
    }
}
