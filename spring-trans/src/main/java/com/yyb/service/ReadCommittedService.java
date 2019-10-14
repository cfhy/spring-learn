package com.yyb.service;

import com.yyb.base.BaseService;
import com.yyb.mapper.CountryMapper;
import com.yyb.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true,rollbackFor = {RuntimeException.class, Exception.class} )
public class ReadCommittedService extends BaseService<Country> {
    @Autowired
    CountryMapper countryMapper;

    /**
     * 测试读已提交
     */
    @Transactional(readOnly = false, rollbackFor = {Exception.class},isolation = Isolation.READ_COMMITTED)
    public void test1() {
        //先查询，结果为1000
        Country china = countryMapper.findOne(1);
        System.out.println("test1-1="+china);
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //睡眠期间，test2操作后，再查为0
        china = countryMapper.findOne(1);
        System.out.println("test1-2="+china);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class},isolation = Isolation.READ_COMMITTED)
    public void test2() {
        Country china = countryMapper.findOne(1);
        System.out.println("test2-1="+china);
        china.setCountrymonely(china.getCountrymonely()-1000);
        countryMapper.update(china);
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2-2="+china);
    }

//    test1-1=Country{id=1, countryname='中国', countrycode='CN', countrymonely=1000}
//    test2-1=Country{id=1, countryname='中国', countrycode='CN', countrymonely=1000}
//    test2-2=Country{id=1, countryname='中国', countrycode='CN', countrymonely=0}
//    test1-2=Country{id=1, countryname='中国', countrycode='CN', countrymonely=0}
}
