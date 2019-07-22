package com.yyb.springannonation.config;

import com.yyb.springannonation.model.Book;
import com.yyb.springannonation.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class BookConfig {
    @Bean
    public Book book() {
        int choice = (int) Math.floor(Math.random() * 4);
        String name = "";
        if (choice == 0) {
            name = "算法";
        } else if (choice == 1) {
            name = "设计模式";
        } else {
            name = "Spring 实战";
        }
        return new Book(name);
    }
}
