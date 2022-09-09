package com.xiaohui.verb.controller.common;

import lombok.Data;

@Data
public  class BaseResponse {
    private boolean flag;//成功或失败
    private Object data;// 返回数据
    private Integer code;// 返回码
    private String msg;// 错误信息

    public BaseResponse() {
    }


    public BaseResponse(Integer code,boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
        this.code = code;
    }


    public BaseResponse(Object data) {
        this.data = data;
    }


    public BaseResponse(boolean flag) {
        this.flag = flag;
    }

    //    异常拦截后给出msg消息
    public BaseResponse(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }


    //    成功返回
    public static BaseResponse ok(Object data){
        return new BaseResponse(200,true,data);
    }

    //    失败返回
    public static BaseResponse faild(Object data){
        return new BaseResponse(500,false,data);
    }

    //   权限不足
    public static BaseResponse forbidden (Object data){
        return new BaseResponse(403,false,data);
    }
}
