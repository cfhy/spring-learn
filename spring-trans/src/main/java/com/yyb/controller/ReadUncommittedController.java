package com.yyb.controller;

import com.yyb.common.response.ResponseData;
import com.yyb.service.ReadUncommittedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("readUncommit")
public class ReadUncommittedController {

    @Autowired
    private ReadUncommittedService readUncommittedService;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public ResponseData testReadUncommitted1(){
        readUncommittedService.test1();
        return ResponseData.success();
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public ResponseData testReadUncommitted2(){
        readUncommittedService.test2();
        return ResponseData.success();
    }
}
