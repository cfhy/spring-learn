package com.yyb.springannonation.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Computer {
    @Value("macbook")
    private String name;

    public Computer(){}

    public Computer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
