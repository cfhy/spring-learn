package com.yyb.springannonation.controller;

import com.yyb.springannonation.config.JavaConfig;
import com.yyb.springannonation.model.Person;
import com.yyb.springannonation.model.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        Student student = annotationConfigApplicationContext.getBean(Student.class);
        student.read();
    }
}
