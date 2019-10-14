package com.yyb.springmvc.service;

import com.yyb.springmvc.model.Spittle;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

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
