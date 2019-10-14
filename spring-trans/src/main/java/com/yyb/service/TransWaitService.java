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
public class TransWaitService extends BaseService<Country> {
    @Autowired
    CountryMapper countryMapper;

    /**
     * 测试多个事务对同一条数据进行操作，需要等待的情况
     */
    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public void test1() {
        Country china = countryMapper.findOne(1);
        china.setCountrymonely(china.getCountrymonely()-100);
        countryMapper.update(china);

        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1操作完成");
    }

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public void test2() {
        Country china = countryMapper.findOne(1);
        System.out.println("test2-1="+china);
        long start=System.currentTimeMillis();
        china.setCountrymonely(china.getCountrymonely()-1000);
        countryMapper.update(china);
        long time=System.currentTimeMillis()-start;
        System.out.println("test2修改花费了="+time+"ms");
        System.out.println("test2-2="+china);
    }
//    访问test1后马上访问test2:，执行结果如下：

//    test2-1=Country{id=1, countryname='中国', countrycode='CN', countrymonely=-100}
//    test1操作完成
//2019-10-11 18:08:06.938 ERROR 11084 --- [nio-8080-exec-2] c.alibaba.druid.filter.stat.StatFilter   : slow sql 19261 millis. update country
//    SET countryname = ?, countrycode = ?, countrymonely = ? where id = ?["中国","CN",-1100,1]
//    test2修改花费了=19265ms
//    test2-2=Country{id=1, countryname='中国', countrycode='CN', countrymonely=-1100}
}
