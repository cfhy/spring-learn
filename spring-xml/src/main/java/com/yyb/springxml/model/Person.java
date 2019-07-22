package com.yyb.springxml.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Person {
    private String name;
    private Computer computer;
    private List<String> hobbyList;

    public Person() {    }

    public Person(String name, Computer computer, List<String> hobbyList) {
        this.name = name;
        this.computer = computer;
        this.hobbyList=hobbyList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public List<String> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<String> hobbyList) {
        this.hobbyList = hobbyList;
    }

    public void networking() {
        System.out.println(name + "使用" + computer.getName() + "上网学习");
        System.out.println(String.join(",",hobbyList));
    }

}
