package com.yyb.controller.propagation;

/**
 * REQUIRED,REQUIRES_NEW,NESTED异同
 *
 * NESTED和REQUIRED修饰的内部方法都属于外围方法事务，如果外围方法抛出异常，这两种方法的事务都会被回滚。
 * 但是REQUIRED是加入外围方法事务，所以和外围事务同属于一个事务，一旦REQUIRED事务抛出异常被回滚，外围方法事务也将被回滚。
 * 而NESTED是外围方法的子事务，有单独的保存点，所以NESTED方法抛出异常被回滚，不会影响到外围方法的事务。
 *
 * NESTED和REQUIRES_NEW都可以做到内部方法事务回滚而不影响外围方法事务。但是因为NESTED是嵌套事务，所以外围方法回滚之后，作为外围方法事务的子事务也会被回滚。
 * 而REQUIRES_NEW是通过开启新的事务实现的，内部事务和外围事务是两个事务，外围事务回滚不会影响内部事务。
 *
 *
 * 事务属性之7种传播行为：https://blog.csdn.net/soonfly/article/details/70305683
 */
public class Conclusion {

}

//模拟用例

//介绍了这么多事务传播行为，我们在实际工作中如何应用呢？下面我来举一个示例：
//假设我们有一个注册的方法，方法中调用添加积分的方法，如果我们希望添加积分不会影响注册流程（即添加积分执行失败回滚不能使注册方法也回滚），我们会这样写：

//@Service
//public class UserServiceImpl implements UserService {
//
//    @Transactional
//    public void register(User user){
//
//        try {
//            membershipPointService.addPoint(Point point);
//        } catch (Exception e) {
//            //省略...
//        }
//        //省略...
//    }
//    //省略...
//}

// 我们还规定注册失败要影响addPoint()方法（注册方法回滚添加积分方法也需要回滚），那么addPoint()方法就需要这样实现：

//@Service
//public class MembershipPointServiceImpl implements MembershipPointService{
//
//    @Transactional(propagation = Propagation.NESTED)
//    public void addPoint(Point point){
//
//        try {
//            recordService.addRecord(Record record);
//        } catch (Exception e) {
//            //省略...
//        }
//        //省略...
//    }
//    //省略...
//}

//我们注意到了在addPoint()中还调用了addRecord()方法，这个方法用来记录日志。他的实现如下：
//@Service
//public class RecordServiceImpl implements RecordService{
//
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public void addRecord(Record record){
//        //省略...
//    }
//    //省略...
//}
//我们注意到addRecord()方法中propagation = Propagation.NOT_SUPPORTED，因为对于日志无所谓精确，可以多一条也可以少一条，
//所以addRecord()方法本身和外围addPoint()方法抛出异常都不会使addRecord()方法回滚，并且addRecord()方法抛出异常也不会影响外围addPoint()方法的执行。
//通过这个例子相信大家对事务传播行为的使用有了更加直观的认识，通过各种属性的组合确实能让我们的业务实现更加灵活多样。