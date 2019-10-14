package com.yyb.controller;

import com.yyb.common.response.ResponseData;
import com.yyb.service.ReadCommittedService;
import com.yyb.service.RepeatableReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("repeatableRead")
public class RepeatableReadController {

    @Autowired
    private RepeatableReadService repeatableReadService;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public ResponseData test1(){
        repeatableReadService.test1();
        return ResponseData.success();
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public ResponseData test2(){
        repeatableReadService.test2();
        return ResponseData.success();
    }
}
