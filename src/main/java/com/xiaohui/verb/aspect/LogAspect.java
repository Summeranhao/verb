package com.xiaohui.verb.aspect;

import com.xiaohui.verb.domain.Log;
import com.xiaohui.verb.domain.User;
import com.xiaohui.verb.mapper.TXhLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect//定义切面
@Component
@Order(1)//第一层切面
@Slf4j
public class LogAspect {

    @Resource
    private TXhLogMapper logMapper;

    //扫描RequiredLog注解
    @Pointcut("@annotation(com.xiaohui.verb.aspect.RequiredLog)")
    public void doPointCut() {
    }

    @Around("doPointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        try {
            //记录方法执行前的时间
            long t1 = System.currentTimeMillis();
            //执行方法
            Object result = jp.proceed();
            //记录方法执行后的时间
            long t2 = System.currentTimeMillis();
            //保存用户行为日志
            log(jp, (t2 - t1));
            return result;
        } catch (Throwable e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    private void log(ProceedingJoinPoint jp, long time) throws Exception {
        //获取目标对象
        Class<?> targetCls = jp.getTarget().getClass();
        //获取目标方法签名信息(包含方法名,参数列表等信息)
        MethodSignature ms = (MethodSignature) jp.getSignature();
        //获取方法对象
        Method interfaceMethod = ms.getMethod();
        //获取方法名
        String methodName = interfaceMethod.getName();
        //拼接包名+类名+方法
        String clsMethodName = targetCls.getName() + "." + methodName;
        //获取注解RequiredLog
        Method targetMethod =
                targetCls.getMethod(methodName, ms.getParameterTypes());
        RequiredLog requiredLog =
                targetMethod.getAnnotation(RequiredLog.class);
        //获取方法描述(注解值)
        String operation = requiredLog.value();

        //获取已登录的用户信息
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        User user = (User) session.getAttribute("user");

        //如果是登录操作则获取登录时的用户名作为执行人
        if(user==null&&operation.equals("登录")){
            Object[] args = jp.getArgs();
            for (Object object : args) {
                if (object instanceof User) {
                    user = (User) object;
                }
            }
        }

        //初始化日志实体
        Log entity = new Log()
                .setExecutor(user == null ? "" : user.getUsername())//登录使用的用户名
                .setOperation(operation)
                .setMethod(clsMethodName)//method=类全名+方法名
                .setIp(IPUtils.getIpAddr(null))
                .setExecutionDuration(time)
                .setCreatetime(new Date());
        //插入日志
        logMapper.insert(entity);
    }
}

