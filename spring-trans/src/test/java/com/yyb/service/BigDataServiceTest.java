package com.yyb.service;

import com.yyb.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class BigDataServiceTest {
    @Autowired
    private BigDataService bigDataService;

    @Test
    public void insertTenMillionData() {
        bigDataService.insertTenMillionData();
    }
}