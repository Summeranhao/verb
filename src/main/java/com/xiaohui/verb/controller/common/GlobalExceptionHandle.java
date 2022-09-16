package com.xiaohui.verb.controller.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse errorHandle(Exception e){
        e.printStackTrace();

        return BaseResponse.failed("系统异常");
    }

}
