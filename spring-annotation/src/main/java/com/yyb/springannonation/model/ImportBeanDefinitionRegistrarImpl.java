package com.yyb.springannonation.model;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ImportBeanDefinitionRegistrarImpl implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //把所有要添加到容器中的bean，手动注册
        RootBeanDefinition beanDefinition=new RootBeanDefinition(Book.class);
        registry.registerBeanDefinition("book",beanDefinition);
    }
}
