package com.yyb.controller.propagation;

import com.yyb.model.User1;
import com.yyb.model.User2;
import com.yyb.service.User1Service;
import com.yyb.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 场景二: 外围方法开启事务。
 * 结论：以上试验结果我们证明在外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，
 * 外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
 */
@RestController
@RequestMapping("/test6")
public class NestedTest2 {
    @Autowired
    User1Service user1Service;
    @Autowired
    User2Service user2Service;

    /**
     * 结论：“张三”、“李四”均未插入。
     * 外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
     */
    @RequestMapping("session1")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_nested_nested(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addNested(user2);
        throw new RuntimeException();
    }

    /**
     * 结论：“张三”、“李四”均未插入。
     * 外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
     */
    @RequestMapping("session2")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_nested_nested_exception(){
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addNested(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addNestedException(user2);
    }

    /**
     * 结论：“张三”插入，“李四”未插入。
     * 外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，"李四"回滚，外围方法异常捕获后无法感知异常，致使“张三”这个事务无法回滚。
     */
    @RequestMapping("session3")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception_try(){
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addNested(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        try {
            user2Service.addNestedException(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }
}