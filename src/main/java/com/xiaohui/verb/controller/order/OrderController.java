package com.xiaohui.verb.controller.order;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
    public IPage<Order> selectAllPageQuery(@RequestParam("orderCode") String skuName, @RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize) {
        return orderService.selectAllPageQuery(skuName,pageNum,pageSize);

    }


    @GetMapping ("/queryAll")
    @ResponseBody
    public String getOrderList(){
        List<Order> skus =orderService.queryOrder(null);
        return skus.toString();
    }

    @GetMapping("/query")
    @ResponseBody
    public String getOrder(String userName){

        Order order=new Order();
        order.setUserName(userName);
        List<Order> orders =orderService.queryOrder(order);
        return orders.toString();
    }

    @PostMapping("/del")
    @ResponseBody
    public String deleteOrder(Integer id){
        orderService.deleteOrder( id);
        return "删除成功！";
    }


    @PostMapping("/edit")
    @ResponseBody
    public String editOrder(Order order){
        orderService.editOrder( order);
        return "编辑成功！";
    }


    @PostMapping("/add")
    @ResponseBody
    public String addOrder(Order order){
        orderService.addOrder( order);
        return "新增成功！";
    }

}
