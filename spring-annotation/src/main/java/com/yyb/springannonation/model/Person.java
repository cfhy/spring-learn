package com.yyb.springannonation.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private Computer computer;

//    @Autowired
    public Person(Computer computer) {
        this.computer = computer;
    }

    public void networking() {
        System.out.println("使用" + computer.getName() + "上网学习");
    }

}
