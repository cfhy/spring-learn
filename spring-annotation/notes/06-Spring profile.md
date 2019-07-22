在开发中，经常会区分开发环境和生产环境，比如在开发阶段使用开发环境，而上线之后使用的生成环境，
那么如何方便快捷的切换环境呢？

Spring引入了bean profile的功能。根据环境决定该创建哪个bean和不创建哪个bean。要使用profile，你首
先要将所有不同的bean定义整理到一个或多个profile之中，在将应用部署到每个环境时，要确保对应的profile
处于激活的状态。

### 在java配置中使用profile

在Java配置中，可以使用@Profile注解指定某个bean属于哪一个profile。示例如下：
```java
@Configuration
@Profile("dev")//类中的bean只有在dev profile激活时才会创建
public class BookConfig {
    @Bean
    public Book book() {
        return new Book("Spring 实战");
    }
}
@Configuration
@Profile("prod")//类中的bean只有在prod profile激活时才会创建
public class ComputerConfig {
    @Bean
    public Computer computer() {
        return new Computer("macbook");
    }
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.yyb.springannonation.config.JavaConfig.class)
@ActiveProfiles("dev")//当dev激活时，book对象不为null，computer对象为null
public class JavaProfileConfig {
    @Autowired(required = false)
    private Book book;
    @Autowired(required = false)
    private Computer computer;

    @Test
    public void testProfile() {
        System.out.println(book);
        System.out.println(computer);
    }
}

```
Profile注解既可以在类上使用，也可以在方法级别上使用@Profile注解，与@Bean注解一同使用。

只有当规定的profile激活时，相应的bean才会被创建，没有指定profile的bean始终都会被创建，与激活哪个profile没有关系。

### 在XML中配置profile
可以通过<beans>元素的profile属性，在XML中配置 profile bean，可以定义多个xml，每个xml指定一个profile属性，
也可以定义在一个xml中。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
   
    <beans profile="dev">
        
    </beans>
    <beans profile="prod">
        
    </beans>
</beans>
```
### 激活profile
Spring在确定哪个profile处于激活状态时，需要依赖两个独立的属性：spring.profiles.active和spring.profiles.default。
当active没指定的时候，就使用默认值。如果都没值，那就没有激活的profile。因此只会创建那些没有定义在profile中的bean。

有多种方式来设置这两个属性：
> 作为DispatcherServlet的初始化参数；
> 作为Web应用的上下文参数；
> 作为JNDI条目；
> 作为环境变量；
> 作为JVM的系统属性；
> 在集成测试类上，使用@ActiveProfiles注解设置。

比如在Web应用的web.xml文件中设置默认的profile:
```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee"
   xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
	      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
   version="3.0" metadata-complete="true">

   <display-name>Spring MVC Project</display-name>
   <description>A Spring MVC project using Thymeleaf for the views.</description>

   <context-param>
      <!-- Spring XML context -->
      <param-name>contextClass</param-name>
      <param-value>org.springframework.web.context.support.XmlWebApplicationContext</param-value>
   </context-param>

   <context-param>
      <!-- Spring XML configuration -->
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:context/application-context.xml</param-value>
   </context-param>

   <context-param>
      <param-name>spring.profiles.default</param-name>
      <param-value>dev</param-value>
   </context-param>


   <listener>
      <!-- Starts up and shuts down the context -->
      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
   </listener>

   <servlet>
      <!-- Application servlet -->
      <servlet-name>appServlet</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <init-param>
         <param-name>contextConfigLocation</param-name>
         <param-value>classpath:context/servlet.xml</param-value>
      </init-param>
      <init-param>
         <param-name>spring.profiles.default</param-name>
         <param-value>dev</param-value>
      </init-param>
      <load-on-startup>1</load-on-startup>
   </servlet>

   <servlet-mapping>
      <!-- Application servlet mapping -->
      <servlet-name>appServlet</servlet-name>
      <url-pattern>/</url-pattern>
   </servlet-mapping>

   <error-page>
      <error-code>404</error-code>
      <location>/404</location>
   </error-page>

</web-app>

```
profile使用的都是复数形式。这意味着可以同时激活多个profile，通过列出多个profile名称，并以逗号分隔来实现。
