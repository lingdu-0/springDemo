package com.wb.service;

import com.wb.MyAspect;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceFactory {
    /**
     * jdk实现代理
     *
     * @return
     */
    public static UserService1 createUserService() {
        //1创建目标对象target
        final UserService1 userService1 = new UserService1Impl();
        //2声明切面类对象
        final MyAspect aspect = new MyAspect();
        //3把切面类2个方法应用到目标类
        //创建jdk代理
        //ClassLoader loader,类加载器
        //Class<?>[] interfaces,接口
        //InvocationHandler 处理
        UserService1 service1 = (UserService1) Proxy.newProxyInstance(
                UserServiceFactory.class.getClassLoader(),
                userService1.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //方法返回值是 业务方法的返回值
                        aspect.before();
                        Object obj = method.invoke(userService1, args);
                        System.out.println("拦截了返回值:" + obj);
                        aspect.after();
                        return obj;
                    }
                });
        return service1;
    }

    /**
     * cglib实现代理
     *
     * @return
     */
    public static UserService1 createUserSerivce2() {
        //1创建目标对象target
        final UserService1 userService1 = new UserService1Impl();
        //2声明切面类对象
        final MyAspect aspect = new MyAspect();
        //3创建增强对象
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(userService1.getClass());
        //设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                aspect.before();
                System.out.println(o.getClass());
                System.out.println(methodProxy);
                //放行方法
                //Object obj = method.invoke(userService1, objects);
                Object obj = methodProxy.invokeSuper(o, objects);
                System.out.println("返回值拦截!");
                aspect.after();
                return obj;
            }
        });
        //创建代理对象
        UserService1 serviceProxy = (UserService1) enhancer.create();
        return serviceProxy;
    }
}
