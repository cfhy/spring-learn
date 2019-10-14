package com.yyb.service;

import com.yyb.base.BaseService;
import com.yyb.common.utils.RedisUtils;
import com.yyb.mapper.CountryMapper;
import com.yyb.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true,rollbackFor = {Exception.class} )
public class ReadUncommittedService extends BaseService<Country> {
    @Autowired
    CountryMapper countryMapper;

    /**
     * 测试读已提交
     */
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public void test1() {
        //中国给美国转账
        Country china = countryMapper.findOne(1);
        china.setCountrymonely(china.getCountrymonely()-100);
        countryMapper.update(china);

        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Country usa = countryMapper.findOne(2);
        usa.setCountrymonely(usa.getCountrymonely()+100);
        countryMapper.update(usa);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class},isolation = Isolation.READ_UNCOMMITTED)
    public void test2() {
        Country china = countryMapper.findOne(1);
        Country usa = countryMapper.findOne(2);

//        事务还未提交，其他事务可以读取未提交的值
        System.out.println(china);
        System.out.println(usa);
//        Country{id=1, countryname='中国', countrycode='CN', countrymonely=900}
//        Country{id=2, countryname='美国', countrycode='US', countrymonely=1000}
    }
}
