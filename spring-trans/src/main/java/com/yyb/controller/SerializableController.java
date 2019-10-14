package com.yyb.controller;

import com.yyb.common.response.ResponseData;
import com.yyb.service.RepeatableReadService;
import com.yyb.service.SerializableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("serializable")
public class SerializableController {

    @Autowired
    private SerializableService serializableService;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public ResponseData test1(){
        serializableService.test1();
        return ResponseData.success();
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public ResponseData test2(){
        serializableService.test2();
        return ResponseData.success();
    }
}
