package com.yyb.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author yyb
 * @date 2019/9/3 18:16
 * @description
 */
public class Person implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {


    private String name;

    public Person(){
        System.out.println("2、开始实例化 person ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("3、填充属性，设置 name 属性");
    }

    public void setBeanName(String beanId) {
        System.out.println("5、Person 实现了BeanNameAware接口，Spring调用setBeanName()方法，将Person的ID="+beanId+"传递给setBeanName方法");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("6、Person实现了BeanFactoryAware接口，Spring 调用 setBeanFactory()方法，将 BeanFactory 容器实例传入");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("7、Person实现了ApplicationContextAware接口，Spring调用setApplicationContext()方法，将ApplicationContext容器实例传入");
    }

    public void afterPropertiesSet(){
        System.out.println("10、Person实现了InitializingBean接口，Spring调用它的afterPropertiesSet()方法。");
    }

    public void destroy(){
        System.out.println("15、Person实现了DisposableBean接口，Spring调用它的destroy() 接口方法");
    }

    public void initMethod(){
        System.out.println("11、调用@Bean注解标注的Bean的init-method 方法");
    }

    public void destroyMethod(){
        System.out.println("16、调用@Bean注解标注的Bean的destroy-method 方法");
    }

    // 自定义初始化方法
    @PostConstruct
    public void springPostConstruct(){
        System.out.println("9、执行@PostConstruct注解定义的初始化方法");
    }

    // 自定义销毁方法
    @PreDestroy
    public void springPreDestory(){
        System.out.println("14、执行@PreDestory注解定义的销毁方法");
    }
}