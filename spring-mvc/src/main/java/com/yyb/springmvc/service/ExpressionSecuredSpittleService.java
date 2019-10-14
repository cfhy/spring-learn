package com.yyb.springmvc.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import spittr.domain.Spittle;

public class ExpressionSecuredSpittleService implements SpittleService {

  @Override
  @PreAuthorize("(hasRole('ROLE_SPITTER') and #spittle.text.length() le 140) or hasRole('ROLE_PREMIUM')")
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }

  @Override
  @PostAuthorize("returnObject.spittler.username==principal.username")
  public Spittle getSpittleById() {
   //
  }

  @PostFilter("hasRole('ROLE_ADMIN') || targetObject.spittler.username==principal.username")
  public void deleteSpittles(List<Spittle> spittles){
    //Spittle只能由其所有者或管理员删除
    //@PreFilter注解能够保证传递给deleteSpittles()方法的列表中，只包含当前用户有权限删除的Spittle。
    // 这个表达式会针对集合中的每个元素进行计算，只有表达式计算结果为true的元素才会保留在列表中。
  }
}
