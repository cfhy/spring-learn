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
public class RepeatableReadService extends BaseService<Country> {
    @Autowired
    CountryMapper countryMapper;

    /**
     * 测试可重复读
     */
    @Transactional(readOnly = false, rollbackFor = {Exception.class},isolation = Isolation.REPEATABLE_READ)
    public void test1() {
        Country china = countryMapper.findOne(1);
        System.out.println("test1-1="+china);
        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        china = countryMapper.findOne(1);
        System.out.println("test1-2="+china);
        china.setCountrymonely(china.getCountrymonely()-1000);
        countryMapper.update(china);
        china = countryMapper.findOne(1);
        System.out.println("test1-3="+china);
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class},isolation = Isolation.REPEATABLE_READ)
    public void test2() {
        Country china = countryMapper.findOne(1);
        System.out.println("test2-1="+china);
        china.setCountrymonely(china.getCountrymonely()-2000);
        countryMapper.update(china);
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2-2="+china);
    }
// Mysql在读的时候，可以进行修改，但仍然能保证可重复读，数据和数据库对不上了
//    test1-1=Country{id=1, countryname='中国', countrycode='CN', countrymonely=1000}
//    test2-1=Country{id=1, countryname='中国', countrycode='CN', countrymonely=1000}
//    test2-2=Country{id=1, countryname='中国', countrycode='CN', countrymonely=0}
//    test1-2=Country{id=1, countryname='中国', countrycode='CN', countrymonely=1000}
//    test1-3=Country{id=1, countryname='中国', countrycode='CN', countrymonely=1000}
}
