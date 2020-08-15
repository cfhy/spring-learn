package com.yyb.controller.isolation;

import com.yyb.common.response.ResponseData;
import com.yyb.service.ReadCommittedService;
import com.yyb.service.ReadUncommittedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("readCommitted")
public class ReadCommittedController {

    @Autowired
    private ReadCommittedService readCommittedService;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public ResponseData test1(){
        readCommittedService.test1();
        return ResponseData.success();
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public ResponseData test2(){
        readCommittedService.test2();
        return ResponseData.success();
    }
}
