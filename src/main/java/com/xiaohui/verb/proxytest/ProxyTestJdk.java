package com.xiaohui.verb.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK的动态代理
 */
public class ProxyTestJdk {

    public static void main(String[] args) {

        //创建目标对象
        final Target target = new Target();
        //获得增强对象
        Advice advice = new Advice();

        //返回值 就是动态生成的代理对象
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                //目标对象加载器
                target.getClass().getClassLoader(),
                //目标对象相同的接口字节码对象数组
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    //调用代码方法的任何方法，实质执行的都是 invoke 方法
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //前置增强
                        advice.before();
                        //执行目标方法
                        Object invoke = method.invoke(target, args);
                        //后置增强
                        advice.after();
                        return invoke;
                    }
                }
        );
        //调用代理对象方法
        proxy.save();
        proxy.save2();
    }
}

