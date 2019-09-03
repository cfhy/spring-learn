package com.yyb.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yyb
 * @date 2019/9/3 18:24
 * @description
 */
@Configuration
public class JavaConfig {
    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    public Person person(){
        Person p= new Person();
        p.setName("yyb");
        return p;
    }
    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
      return new MyBeanPostProcessor();
    }
    @Bean
    public MyInstanceBeanPostProcessor myInstanceBeanPostProcessor(){
        return new MyInstanceBeanPostProcessor();
    }
}
