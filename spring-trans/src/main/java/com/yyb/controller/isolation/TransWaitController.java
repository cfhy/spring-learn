package com.yyb.controller.isolation;

import com.yyb.common.response.ResponseData;
import com.yyb.service.ReadUncommittedService;
import com.yyb.service.TransWaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("transWait")
public class TransWaitController {

    @Autowired
    private TransWaitService transWaitService;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public ResponseData test1(){
        transWaitService.test1();
        return ResponseData.success();
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public ResponseData test2(){
        transWaitService.test2();
        return ResponseData.success();
    }
}
