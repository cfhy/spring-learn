package com.yyb.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在Web层的安全配置中设置认证
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        //自定义的安全表达式处理行为
        return super.createExpressionHandler();
    }
}
