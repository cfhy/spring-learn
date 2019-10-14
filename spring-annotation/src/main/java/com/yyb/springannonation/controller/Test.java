package com.yyb.springannonation.controller;

import com.yyb.springannonation.config.JavaConfig;
import com.yyb.springannonation.model.Book;
import com.yyb.springannonation.model.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        Student bean = context.getBean(Student.class);
        context.close();
        System.out.println(bean.getName());
    }
}
