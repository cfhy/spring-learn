在默认情况下，Spring应用上下文中所有bean都是作为以单例的形式创建的，
Spring定义了多种作用域，可以基于这些作用域创建bean，包括：

> 单例（Singleton）：在整个应用中，只创建bean的一个实例。

> 原型（Prototype）：每次注入或者通过Spring应用上下文获取的时候，都会创建一个新的bean实例。

> 会话（Session）：在Web应用中，为每个会话创建一个bean实例。

> 请求（Rquest）：在Web应用中，为每个请求创建一个bean实例。

修改bean为原型bean
```java
@Component
//@Scope("prototype")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AliPay implements IPay {
    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }
}
```
xml方式
```xml
    <bean id="alipay" class="com.yyb.springxml.service.Alipay"  scope="prototype"/>
```
单例在IOC容器启动就创建对象放在容器中;多例在bean每次获取的时候都会创建；

### @lazy注解
懒加载：懒加载只针对单实例bean，容器启动的时候先不创建对象，在第一次获取的时候再创建对象