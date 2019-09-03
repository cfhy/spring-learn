package com.yyb.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yyb
 * @date 2019/9/3 18:24
 * @description
 */
public class Test {
    public static void main(String[] args) {
        // 为面试而准备的Bean生命周期加载过程
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        Person person = (Person)context.getBean("person");
        // 使用属性
        System.out.println("13、实例化完成，此时可获取属性的值：Person name = " + person.getName());
        // 关闭容器
        ((AnnotationConfigApplicationContext) context).close();
    }
}
