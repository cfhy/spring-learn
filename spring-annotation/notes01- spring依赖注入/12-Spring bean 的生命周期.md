1. 实例化Person之前，执行InstantiationAwareBeanPostProcessor接口的postProcessBeforeInstantiation方法
2. 开始实例化 person 
3. 填充属性，设置 name 属性
4. 实例化Person之后，执行InstantiationAwareBeanPostProcessor接口的postProcessAfterInstantiation方法
5. Person 实现了BeanNameAware接口，Spring调用setBeanName()方法，将Person的ID=person传递给setBeanName方法
6. Person实现了BeanFactoryAware接口，Spring 调用 setBeanFactory()方法，将 BeanFactory 容器实例传入
7. Person实现了ApplicationContextAware接口，Spring调用setApplicationContext()方法，将ApplicationContext容器实例传入
8. 初始化 Person 之前，执行BeanPostProcessor接口的postProcessBeforeInitialization方法
9. 执行@PostConstruct注解定义的初始化方法
10. Person实现了InitializingBean接口，Spring调用它的afterPropertiesSet()方法。
11. 调用@Bean注解标注的Bean的init-method 方法
12. 初始化 Person 之后，执行BeanPostProcessor接口的postProcessAfterInitialization方法
13. 实例化完成，此时可获取属性的值：Person name = yyb
14. 执行@PreDestory注解定义的销毁方法
15. Person实现了DisposableBean接口，Spring调用它的destroy() 接口方法
16. 调用@Bean注解标注的Bean的destroy-method 方法
