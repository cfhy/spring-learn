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
public class SerializableService extends BaseService<Country> {
    @Autowired
    CountryMapper countryMapper;

    /**
     * 测试可重复读
     */
    @Transactional(readOnly = false, rollbackFor = {Exception.class},isolation = Isolation.SERIALIZABLE)
    public void test1() {
        Country china = countryMapper.findOne(1);
        System.out.println("test1-1="+china);
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class},isolation = Isolation.SERIALIZABLE)
    public void test2() {
        //会等到读取完后，才能执行操作
        Country A=new Country();
        A.setId(100);
        A.setCountrycode("AA");
        A.setCountryname("AAA");
        A.setCountrymonely(2000);
        countryMapper.insert(A);

        Country china = countryMapper.findOne(1);
        china.setCountrymonely(china.getCountrymonely()-2000);
        countryMapper.update(china);
    }
}
