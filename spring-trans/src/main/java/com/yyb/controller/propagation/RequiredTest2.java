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
 * 1.2 场景二
 * 外围方法开启事务，这个是使用率比较高的场景。
 * 结论：以上试验结果我们证明在外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，
 * 所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
 */
@RestController
@RequestMapping("/test2")
public class RequiredTest2 {
    @Autowired
    User1Service user1Service;
    @Autowired
    User2Service user2Service;

    /**
     * 结论：“张三”、“李四”均未插入。
     * 外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚。
     */
    @RequestMapping("session1")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }

    /**
     * “张三”、“李四”均未插入。
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
     */
    @RequestMapping("session2")
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiredException(user2);
    }

    /**
     * “张三”、“李四”均未插入。
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，即使方法被catch不被外围方法感知，整个事务依然回滚。
     *
     * 会报错：org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
     * 原因是：Propagation.REQUIRED，沿用外层调用方法的事务 （如果有事务存在，则使用原事务，如果不存在则开启新事务）
     * 外层方法开启了一个事务，内层方法发现异常了，会标记整个事务为roll-back，但是外层方法捕获异常return的时候，会执行commit事务，但是此时发现已经标记异常，所以才会出错
     */
    @RequestMapping("session3")
    @Transactional
    public void transaction_required_required_exception_try() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        try {
            user2Service.addRequiredException(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }

    /**
     * “张三”、“李四”均插入。
     * 外围方法开启事务，内部方法没有事务，内部方法抛出异常，外层捕获后，无法感知到异常，所以不会回滚，也不会报错。
     */
    @RequestMapping("session4")
    @Transactional
    public void transaction_required_required_exception_try1() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);
        try {
            User1 user2 = new User1();
            user2.setName("李四");
            user1Service.addRequiredNoTrans(user2);
        } catch (Exception e) {
            System.out.println("方法回滚");
        }
    }
}