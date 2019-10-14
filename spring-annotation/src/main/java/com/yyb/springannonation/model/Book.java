package com.yyb.springannonation.model;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class Book implements InitializingBean, DisposableBean {
    private String name;
    public Book(){
        System.out.println("执行Book的构造方法");
    }

    public  void init(){
        System.out.println("执行Book的初始化方法");
    }

    public  void destory(){
        System.out.println("执行Book的destory方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行Book的InitializingBean接口的afterPropertiesSet方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行Book的DisposableBean接口destroy方法");
    }

    public void postContract(){
        System.out.println("执行Book的postContract方法");
    }

    public void preDestory(){
        System.out.println("执行Book的preDestory方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
