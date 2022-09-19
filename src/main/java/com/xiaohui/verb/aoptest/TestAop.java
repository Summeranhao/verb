package com.xiaohui.verb.aoptest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 切面处理类
 */
@Component
@Aspect
public class TestAop {


    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.xiaohui.verb.aoptest.AopTest)")
    public void apoPointCut() {
        // 无方法体，主要在@Pointct中体现@AopTest注解类所在位置
    }

    /**
     * 配置环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("apoPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 注解类可能被代理，需要获取真实类的注解
        Class<?> targetClass = joinPoint.getTarget().getClass();
        Method method = targetClass.getDeclaredMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        // 获取方法注解
        AopTest aopTest = method.getAnnotation(AopTest.class);
        // 获取注解参数
        int type = aopTest.type();
        String value = aopTest.value();

        //获取方法参数
        Parameter[] parameters = method.getParameters();

        // 后续业务处理
        // do something....

        // 方法执行
        return joinPoint.proceed();
    }


    /**
     * 配置异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "apoPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        // 对应业务处理
        // do something..
    }
}

