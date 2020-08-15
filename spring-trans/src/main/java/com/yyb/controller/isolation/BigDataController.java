package com.yyb.controller.isolation;

import com.yyb.common.response.ResponseData;
import com.yyb.service.BigDataService;
import com.yyb.service.TransWaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("bigData")
public class BigDataController {

    @Autowired
    private BigDataService bigDataService;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public ResponseData test1(){
        bigDataService.insertTenMillionData();
        return ResponseData.success();
    }
}
