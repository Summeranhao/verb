package com.xiaohui.verb.service.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaohui.verb.domain.Order;
import com.xiaohui.verb.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> queryOrder(Order order){

        QueryWrapper<Order> queryWrapper = new  QueryWrapper<>();

        List<Order> orders = orderMapper.selectList(queryWrapper);

        return orders;

    }


    public void deleteOrder(Integer id) {
        orderMapper.deleteById(id);
    }

    public void editOrder(Order order) {
        orderMapper.updateById(order);
    }

    public void addOrder(Order order) {
        orderMapper.insert(order);
    }

    public IPage<Order> selectAllPageQuery(String name, int pageNum, int pageSize) {

        Page page=new Page();
        page.setPages(pageNum);
        page.setTotal(pageSize);

        QueryWrapper<Order> queryWrapper = new  QueryWrapper<>();


        // 分页查询
        IPage iPage = orderMapper.selectPage(page, queryWrapper);

        return iPage;

    }
}
