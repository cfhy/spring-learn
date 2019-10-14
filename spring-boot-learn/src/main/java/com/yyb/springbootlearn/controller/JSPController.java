package com.yyb.springbootlearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JSPController {
    @RequestMapping("/jsp")
    public String Index(){
        return "index";
    }
}
