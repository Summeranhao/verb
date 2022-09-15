package com.xiaohui.verb.interceptor;

import com.xiaohui.verb.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @describe Session拦截器
 */
public class SessionInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);

    @Value("${verb.auth.control.formula}")
    private boolean authRuller;

    /**
     * 实现一个权限控制方案：
     *   id为单数的人可以新增订单、商品
     *   id为双数的人可以修改订单、商品
     *   id为10的倍数的人可以删除订单、商品
     *   名字中带"xhh"的可以操作人员相关接口
     *   以上规则可以支持通过配置改动规则（比如改成单数id和双数id权限互换），不需要修改代码
     */
    @Override                                      //获取请求 和发送请求到服务器中   定义类的名称
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取类的名称  赋值给requestURI  参数 进行相关的调用
        String requestURI = request.getRequestURI();
        //在控制台输出  获取到的所有的相关的路径
        LOGGER.info("Session 检查，请求 URI：" + requestURI);
        // 再次获取参数赋值给uri的问题
        String uri = request.getRequestURI();
        //判断非空
        if (uri != null) {
            //todo 判断是否的登陆的首页  登陆则通过
            //String类型有一个方法：contains（）,该方法是判断字符串中是否有子字符串。如果有则返回true，如果没有则返回false。
            if (uri.contains("/user/login")) {
                return true;

            }
            //todo 判断是否已经登录    此处的("user") 是登陆中获取的，判断是否登陆
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                //重定向页面 如果没有登录就跳转到登录页面   page/login是我的登陆页面
                response.sendRedirect(request.getContextPath() + "/page/login");
                //停止 运行
                return false;
            }else{
                //权限控制
              return authControl(response,user,uri);
            }
        }
        //继续 运行
        return true;
    }

    private boolean authControl(HttpServletResponse response, User user,String url) {

        //取配置文件里配置的权限表达式
        LOGGER.info("配置文件里的权限表达式为："+authRuller);


        if(authRuller && (url.contains("/order/add") || url.contains("/sku/add"))){
            response.setStatus(403);
            return false;
        }

        if(!authRuller && (url.contains("/order/edit") || url.contains("/sku/edit"))){
            response.setStatus(403);
            return false;
        }

        if(user.getId()%10!=0 && (url.contains("/order/del") || url.contains("/sku/del"))){
            response.setStatus(403);
            return false;
        }

        if(!user.getUsername().contains("xhh") && (url.contains("/user/add") || url.contains("/user/edit"))){
            response.setStatus(403);
            return false;
        }

        return true;


    }

    /**
     * 处理成功才进入post处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //LOGGER.info(">>>>>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

    }

    /**
     * 处理完后进入，不论是否抛除异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //LOGGER.info(">>>>>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}