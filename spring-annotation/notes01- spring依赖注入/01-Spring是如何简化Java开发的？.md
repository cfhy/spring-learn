### Spring是如何简化Java开发的？

#### 基于POJO的轻量级和最小侵入性编程
Spring竭力避免因自身的API而弄乱你的应用代码。不会强迫你实现Spring规范的接口或继承Spring规范的类，相反，在
基于Spring构建的应用中，它的类通常没有任何痕迹表明你使用了Spring。可能会使用Spring注解，但它依然是POJO。

#### 通过依赖注入和面向接口实现松耦合
在传统开发中，当多个类协作共同完成某个功能时，往往是直接new这些类的对象，这样类与类之间紧紧的耦合在一起。
紧密耦合的代码难以测试、难以复用、难以理解，修复一个bug，将会出现更多新的bug。通过依赖注入，对象的依赖关系
将由系统中负责协调各对象的第三方组件在创建对象的时候进行设定。对象无需自行创建或管理它们的依赖关系。
如果一个对象只通过接口来表明依赖关系，那么这种依赖就能够在对象本身毫不知情的情况下，用不同的具体实现进行替换。

创建应用组件之间协作的行为通常称为装配，Spring可以采用xml和注解的方式装配bean，比如：
```xml
    <bean id="computer" class="com.yyb.springxml.model.Computer">
        <constructor-arg name="name" value="macbook"/>
    </bean>
    <!--Person依赖Computer，创建Person的时候会自动注入Computer    -->
    <bean id="person" class="com.yyb.springxml.model.Person">
        <constructor-arg name="name" value="yyb"/>
        <!--注入Computer-->
        <constructor-arg name="computer" ref="computer"/>
    </bean>
```
或者
```java
public class JavaProfileConfig {
    @Bean
    public Person person(){
        return new Person("yyb",computer());
    }

    @Bean
    public Computer computer(){
        return  new Computer("macbook");
    }
}
``` 
#### 基于切面和惯例进行声明式编程
AOP可以把遍布应用各处的功能分离出来形成可重用的组件。面向切面编程往往被定义为促使软件系统实现关注点分离的一项技术。
比如最常用的事务，如果不用AOP，那么事务的代码会散布在各个组件中，如果某天要改这些代码，必须修改各个模块的相关实现。
当然可以把事务封装成方法，但是方法的调用还是会重复出现在各个模块。组件会因为那些与自身核心业务无关的代码而变得混乱。

AOP能够使诸如日志、事务管理、安全这样的服务模块化，并以声明的方式将他们应用到他们需要影响的组件中去。你的核心应用甚
至根本不知道他们的存在。这样就确保了POJO的简单性。

#### 通过切面和模板减少样板式代码
Spring旨在通过模板封装来消除样板式代码。Spring的JdbcTemplate使得执行数据库操作时，避免传统的JDBC样板代码成为了可能。


