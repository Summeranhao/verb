package com.xiaohui.verb.proxytest.factory;

import com.xiaohui.verb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * CGLIB动态代理方式
 */
@Component
public class CglibProxyFactory {

    @Autowired
    private UserService userService;

    public UserService createUserserviceCglibProxy(){

        UserService userServiceProxy=null;

        userServiceProxy=(UserService) Enhancer.create(userService.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                Object result=null;

                try {
                    //1.开启事务
                    //2，业务操作
                    result=method.invoke(userService,objects);
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
