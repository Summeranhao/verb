package com.xiaohui.verb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohui.verb.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
