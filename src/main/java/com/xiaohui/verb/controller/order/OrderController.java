package com.xiaohui.verb.controller.order;

import com.xiaohui.verb.controller.common.BaseResponse;
import com.xiaohui.verb.domain.Order;
import com.xiaohui.verb.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    // 多个查询(分页）
    @GetMapping("/selectAllPageQuery")
    public BaseResponse selectAllPageQuery(@RequestParam("orderCode") String skuName, @RequestParam("pageNum") int pageNum,
                                               @RequestParam("pageSize") int pageSize) {
        return BaseResponse.ok(orderService.selectAllPageQuery(skuName,pageNum,pageSize));

    }


    @GetMapping ("/queryAll")
    @ResponseBody
    public BaseResponse getOrderList(){
        List<Order> skus =orderService.queryOrder(null);
        return BaseResponse.ok(skus);
    }

    @GetMapping("/query")
    @ResponseBody
    public BaseResponse getOrder(String userName){

        Order order=new Order();
        order.setUserName(userName);
        List<Order> orders =orderService.queryOrder(order);
        return BaseResponse.ok(orders);
    }

    @PostMapping("/del")
    @ResponseBody
    public BaseResponse deleteOrder(Integer id){
        orderService.deleteOrder( id);
        return BaseResponse.ok(null);
    }


    @PostMapping("/edit")
    @ResponseBody
    public BaseResponse editOrder(Order order){
        orderService.editOrder( order);
        return BaseResponse.ok(null);
    }


    @PostMapping("/add")
    @ResponseBody
    public BaseResponse addOrder(Order order){// TODO: 2022/9/16 基于注解的参数校验
        orderService.addOrder( order);
        return BaseResponse.ok(null);
    }

}
