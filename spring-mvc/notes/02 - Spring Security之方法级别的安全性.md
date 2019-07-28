### 使用注解保护方法
Spring Security提供了三种不同的安全注解：
> Spring Security自带的@Secured注解；

> JSR-250的@RolesAllowed注解；

> 表达式驱动的注解，包括@PreAuthorize、@PostAuthorize、@PreFilter和@PostFilter。

@Secured和@RolesAllowed方案非常类似，能够基于用户所授予的权限限制对方法的访问。
当我们需要在方法上定义更灵活的安全规则时，Spring Security提供了@PreAuthorize和@PostAuthorize，
而@PreFilter/@PostFilter能够过滤方法返回的以及传入方法的集合。

### 使用@Secured注解限制方法调用

在Spring中，如果要启用基于注解的方法安全性，关键之处在于要在配置类上使用@EnableGlobalMethodSecurity

@Secured注解会使用一个String数组作为参数。每个String值是一个权限，调用这个方法至少需要具备其中的一个权限。
```java
public class SecuredSpittleService implements SpittleService {
//通过传递进来ROLE_SPITTER，我们告诉Spring Security只允许具有ROLE_SPITTER权限的认证用户才能调用addSpittle ()方法。
  @Override
  @Secured({"ROLE_SPITTER", "ROLE_ADMIN"})
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }
  
}
```
如果方法被没有认证的用户或没有所需权限的用户调用，保护这个方法的切面将抛出一个Spring Security异常。
@Secured注解的不足之处在于它是Spring特定的注解。如果更倾向于使用Java标准定义的注解，那么你应该考虑使用@RolesAllowed注解

如果选择使用@RolesAllowed的话，需要将@EnableGlobalMethodSecurity的jsr250Enabled属性设置为true，以开启此功能。

### 使用表达式实现方法级别的安全性
Spring Security 3.0引入了几个新注解，它们使用SpEL能够在方法调用上实现更有意思的安全性约束。

> @PreAuthorize 在方法调用之前，基于表达式的计算结果来限制对方法的访问

> @PostAuthorize 允许方法调用，但是如果表达式计算结果为false，将抛出一个安全性异常

> @PostFilter 允许方法调用，但必须按照表达式来过滤方法的结果

> @PreFilter 允许方法调用，但必须在进入方法之前过滤输入值

首先，我们需要将@EnableGlobalMethod-Security注解的prePostEnabled属性设置为true，从而启用它们。

```java
public class ExpressionSecuredSpittleService implements SpittleService {
//Spittr应用程序的一般用户只能写140个字以内的Spittle，而付费用户不限制字数。
//表达式中的#spittle部分直接引用了方法中的同名参数。这使得  Spring Security能够检查传入方法的参数，并将这些参数用于认证决策的制定。
  @Override
  @PreAuthorize("(hasRole('ROLE_SPITTER') and #spittle.text.length() le 140) or hasRole('ROLE_PREMIUM')")
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }
  
}
```
```java
// 假设我们想对getSpittleById()方法进行保护，确保返回的Spittle对象属于当前的认证用户。
// 我们只有得到Spittle对象之后，才能判断它是否属于当前用户。

// 表达式到内置的principal对象中取出其username属性。principal是另一个Spring Security内置的特殊名称，它代表了当前认证用户的主要信息
  @PostAuthorize("returnObject.spittler.username==principal.username")
  public Spittle getSpittleById() {
   //
  }
```
### 过滤方法的输入和输出
有时需要保护的并不是对方法的调用，需要保护的是传入方法的数据和方法返回的数据。
```java
  @PostFilter("hasRole('ROLE_ADMIN') || filterObject.spittler.username==principal.username")
  public List<Spittle> getOffensiveSpittles(){
    //管理员能够看到所有攻击性的Spittle，非管理员只能看到属于自己的Spittle
    //filterObject对象引用的是这个方法所返回List中的某一个元素
  }
```
@PreFilter也使用SpEL来过滤集合，只有满足SpEL表达式的元素才会留在集合中。
但是它所过滤的不是方法的返回值，@PreFilter过滤的是要进入方法中的集合成员。

  @PostFilter("hasRole('ROLE_ADMIN') || targetObject.spittler.username==principal.username")
  public void deleteSpittles(List<Spittle> spittles){
    //Spittle只能由其所有者或管理员删除
    //@PreFilter注解能够保证传递给deleteSpittles()方法的列表中，只包含当前用户有权限删除的Spittle。
    // 这个表达式会针对集合中的每个元素进行计算，只有表达式计算结果为true的元素才会保留在列表中。   
  }
### 定义许可计算器
```java
public class SpittlePermissionEvaluator implements PermissionEvaluator {
    GrantedAuthority ADMIN_AUTHORITY = new GrantedAuthorityImpl("ROLE_ADMIN");

    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        if(target instanceof Spittle){
            Spittle spittle =(Spittle) target;
            String username = spittle.getSpitter().getUsername();
            if("delete".equals(permission)){
                return isAdmin(authentication) || username.equals(authentication.getName());
            }
        }
        throw new UnsupportedOperationException("没有访问权限");
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        throw new UnsupportedOperationException("没有访问权限");
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().contains(ADMIN_AUTHORITY);
    }
}
```















