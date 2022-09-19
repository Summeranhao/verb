package com.xiaohui.verb.proxytest.factory;

import com.xiaohui.verb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理方式
 */
@Component
public class JdkProxyFactory {

    @Autowired
    private UserService userService;


    public UserService createUserServieJdkProxy(){
        UserService userServiceProxy=null;

        userServiceProxy=(UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result=null;
                try {
                    //1.开启事务

                    //2，业务操作
                    result=method.invoke(userService,args);
                    //3，提交事务
                } catch (Exception e) {
                    e.printStackTrace();
                    //4,混滚事务
                } finally {
                    //5，释放资源
                }
                return result;
            }
        });
        return userServiceProxy;
    }

}
