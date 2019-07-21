尽管在很多场景下通过组件扫描和自动装配实现Spring的自动化配置是更为推荐的方式，但有时候自动化配置的方案行不通，
因此需要明确配置Spring。比如说，你想要将第三方库中的组件装配到你的应用中，在这种情况下，是没有办法在它的类上
添加@Component和@Autowired注解的，因此就不能使用自动化装配的方案了。在这种情况下，你必须要采用显式装配的方式。

示例代码如下：
```java
@Configuration
public class BeanConfig {
    @Bean
    public Student student(){
        return new Student("yyb",book());
    }

    @Bean
    public Book book(){
        return new Book("Spring 实战");
    }
}

@Configuration
@ComponentScan(basePackages ={ "com.yyb.springannonation.model", "com.yyb.springannonation.config"})
public class JavaConfig {

}

public class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }
}

public class Student {
    private String name;
    private Book book;

    public Student(String name, Book book) {
        this.name = name;
        this.book = book;
    }

    public void read() {
        System.out.println(name + "正在读" + book.getName());
    }
}    

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        Student student = annotationConfigApplicationContext.getBean(Student.class);
        student.read();
    }
}
```
@Configuration注解表明这个类是一个配置类，该类应该包含在Spring应用上下文中如何创建bean的细节。

@Bean注解会告诉Spring这个方法将会返回一个对象，该对象要注册为Spring应用上下文中的bean。方法体中
包含了最终产生bean实例的逻辑。

默认情况下，bean的ID与带有@Bean注解的方法名是一样的。可以通过name属性指定一个不同的名字。@Bean("Student")

装配bean的最简单方式就是引用创建bean的方法,比如 new Student("yyb",book());由于book()方法上添加了@Bean注解，
Spring将会拦截所有对它的调用，并确保直接返回该方法所创建的bean，而不是每次都对其进行实际的调用。

也可以像下面这样装配，通过这种方式引用其他的bean通常是最佳的选择，因为它不会要求将Book与Student声明到同一个配置类之中。
也不会要求必须用显示配置的方式。
```java
    @Bean
    public Student student(Book book) {
        return new Student("yyb", book);
    }
```

当创建bean的过程业务逻辑非常复杂时，使用@Bean的方式就非常合适。比如：
```java
    @Bean
    public Book book() {
        int choice = (int) Math.floor(Math.random() * 4);
        String name = "";
        if (choice == 0) {
            name = "算法";
        } else if (choice == 1) {
            name = "设计模式";
        } else {
            name = "Spring 实战";
        }
        return new Book(name);
    }
```
