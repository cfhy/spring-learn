package com.yyb.springannonation.config;


import com.yyb.springannonation.model.BeanPostProcessorImpl;
import com.yyb.springannonation.model.Book;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource(value = "classpath:app.properties",encoding = "UTF-8")
@ComponentScan(basePackages = "com.yyb.springannonation.model")
public class JavaConfig {
    @Bean(initMethod = "init",destroyMethod = "destory")
    public Book book(){
        return new Book();
    }

    @Bean
    public BeanPostProcessorImpl beanPostProcessor(){
        return new  BeanPostProcessorImpl();
    }

}
