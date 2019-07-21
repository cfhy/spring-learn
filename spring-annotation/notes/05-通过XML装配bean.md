Spring自动化装配和显式配置以及XML配置这三种方式可以混合使用，自动装配的时候，它并不在意要装配的bean来自哪里。

### <bean>标签
<bean>元素类似于JavaConfig中的@Bean注解。需要知道id和class属性。如果没有明确给定ID，这个bean将会根据全限定类名
来进行命名。形如：com.yyb.springxml.model.Person#0，为了便于被其他bean引用，最好手动指定id。

使用xml的方式不再需要直接负责创建某个类的实例；bean的类型以字符串的形式设置在了class属性中，编译期无法感知到错误。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <bean id="computer" class="com.yyb.springxml.model.Computer">
        <constructor-arg name="name" value="macbook"/>
    </bean>
    
    <bean id="person" class="com.yyb.springxml.model.Person">
        <constructor-arg name="name" value="yyb"/>
        <constructor-arg name="computer" ref="computer"/>
    </bean>

</beans>
```

### 借助构造器注入初始化bean
两种基本的配置方案可供选择:

> <constructor-arg>元素

> 使用Spring 3.0所引入的c-命名空间

两者的区别在很大程度就是是否冗长烦琐,有些事情<constructor-arg>可以做到，但是使用c-命名空间却无法实现。
```xml
    <bean id="person" class="com.yyb.springxml.model.Person">
       <!--将字面量注入到构造器中-->
        <constructor-arg name="name" value="yyb"/>
       <!--将引用注入到构造器中-->
        <constructor-arg name="computer" ref="computer"/>
    </bean>

    <!--使用c-命名空间，参数名的方式-->
    <!--computer：构造器参数名 ref:注入bean引用 -->
    <bean class="com.yyb.springxml.model.Person" c:name="yyb" c:computer-ref="computer" />
    <!--使用c-命名空间，参数位置的方式-->
    <bean class="com.yyb.springxml.model.Person" c:_0="yyb" c:_1-ref="computer" />
    <!--当只有一个构造器参数时，可以直接c:_-->
    <bean id="computer" class="com.yyb.springxml.model.Computer" c:_="yyb" />
```
### 装配集合
