package com.wb;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类:增强代码域切入点结合
 */
public class MyAspect {
    public void before(JoinPoint jp) {
        System.out.println(jp.getSignature().getName() + "方法:开始执行!");
    }

    public void before() {
        System.out.println("事务开启!");
    }

    public void after() {
        System.out.println("事务关闭!");
    }

    public void after(JoinPoint jp) throws Throwable {
        System.out.println(jp.getSignature().getName() + "方法:执行结束!");
    }

    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(pjp.getSignature().getName() + "方法:环绕开始!");
        Object object = pjp.proceed();
        System.out.println(pjp.getSignature().getName() + "方法:环绕结束!");
        return object;
    }
}
