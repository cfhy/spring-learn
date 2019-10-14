package com.yyb.springbootlearn.controller;

import com.yyb.springbootlearn.model.CountryDto;
import com.yyb.springbootlearn.model.GroupDto;
import com.yyb.springbootlearn.model.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController  {
    @Autowired
    private StudentDto studentDto;
    @Autowired
    private CountryDto countryDto;
    @Autowired
    private GroupDto groupDto;

    @GetMapping("some")
    public String Message(){
        return studentDto.getName();
    }
}
