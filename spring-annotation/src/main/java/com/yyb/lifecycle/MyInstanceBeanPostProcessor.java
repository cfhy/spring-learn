package com.yyb.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;


/**
 * @author yyb
 * @date 2019/9/3 18:16
 * @description
 */
public class MyInstanceBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanClass.equals( Person.class)){
            System.out.println("1、实例化Person之前，执行InstantiationAwareBeanPostProcessor接口的postProcessBeforeInstantiation方法");
        }
        return null;
    }

    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(bean instanceof  Person){
            System.out.println("4、实例化Person之后，执行InstantiationAwareBeanPostProcessor接口的postProcessAfterInstantiation方法");
        }
        return false;
    }
}
