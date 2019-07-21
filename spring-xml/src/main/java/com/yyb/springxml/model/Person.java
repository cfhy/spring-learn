package com.yyb.springxml.model;

import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name;
    private Computer computer;

    public Person(String name, Computer computer) {
        this.name = name;
        this.computer = computer;
    }

    public void networking() {
        System.out.println(name + "使用" + computer.getName() + "上网学习");
    }

}
