package com.yyb.springxml.model;

import org.springframework.stereotype.Component;

@Component
public class Computer {
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
