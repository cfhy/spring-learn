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
@RequestMapping("/test3")
public class RequiresNewTest1 {
    @Autowired
    User1Service user1Service;
    @Autowired
    User2Service user2Service;

    /**
     * 结论：“张三”插入，“李四”插入。
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,外围方法抛出异常回滚不会影响内部方法。
     */
    @RequestMapping("session1")
    public void notransaction_exception_requiresNew_requiresNew(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addRequiresNew(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addRequiresNew(user2);
        throw new RuntimeException();

    }

    /**
     * “张三”插入，“李四”未插入
     * 外围方法没有开启事务，插入“张三”方法和插入“李四”方法分别开启自己的事务，插入“李四”方法抛出异常回滚，其他事务不受影响。
     */
    @RequestMapping("session2")
    public void transaction_required_required_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addRequiredException(user2);
    }
}