package com.yyb.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author yyb
 * @date 2019/9/3 18:16
 * @description
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    // 容器加载的时候会加载一些其他的 bean，会调用初始化前和初始化后方法
    // 这次只关注 Person 的生命周期
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Person){
            System.out.println("8、初始化 Person 之前，执行BeanPostProcessor接口的postProcessBeforeInitialization方法");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Person){
            System.out.println("12、初始化 Person 之后，执行BeanPostProcessor接口的postProcessAfterInitialization方法");
        }
        return bean;
    }

}
