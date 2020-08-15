package com.yyb.controller.propagation;

import com.yyb.model.User1;
import com.yyb.model.User2;
import com.yyb.service.User1Service;
import com.yyb.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 场景一: 外围方法没有开启事务。
 * 结论：通过这两个方法我们证明了在外围方法未开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
 */
@RestController
@RequestMapping("/test5")
public class NestedTest1 {
    @Autowired
    User1Service user1Service;
    @Autowired
    User2Service user2Service;

    /**
     * 结论：“张三”、“李四”均插入。
     * 外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     */
    @RequestMapping("session1")
    public void notransaction_exception_nested_nested(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addNested(user2);

        throw new RuntimeException();
    }

    /**
     * 结论：“张三”插入，“李四”未插入。
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
     */
    @RequestMapping("session2")
    public void notransaction_nested_nested_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addNestedException(user2);
    }
}