@Conditional注解，它可以用到带有@Bean注解的方法上。如果给定的条件计算结果为true，就会创建这个bean，
否则的话，这个bean会被忽略。

@Conditional中给定了一个Class，它指明了条件。设置给@Conditional的类可以是任意实现了Condition接口的类型。
如果matches()方法返回true，那么就会创建带有@Conditional注解的bean。如果matches()方法返回false，将不会创建这些bean。

@Profile本身也使用了@Conditional注解。

```java
@Configuration
public class JavaConfig {
//    @Bean
//    public Book book(){
//        return new Book("Spring 实战");
//    }

    @Conditional(BookExistsCondition.class)
    @Bean
    public Computer computer(){
        return new Computer("macbook");
    }
}

//当Book对象存在时，才实例化Computer对象
public class BookExistsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        Map<String, Book> beansOfType = beanFactory.getBeansOfType(Book.class);
        return beansOfType!=null && beansOfType.size()>0;
    }
}

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        //当把book方法注释掉时，computer对象也不会被创建
        Computer computer = annotationConfigApplicationContext.getBean(Computer.class);
        System.out.println(computer);
    }
}
```