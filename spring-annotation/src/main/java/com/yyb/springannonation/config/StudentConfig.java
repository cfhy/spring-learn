package com.yyb.springannonation.config;

import com.yyb.springannonation.model.Book;
import com.yyb.springannonation.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    public Student student(Book book) {
        return new Student("yyb", book);
    }
}
