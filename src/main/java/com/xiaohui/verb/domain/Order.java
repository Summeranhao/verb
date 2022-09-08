package com.xiaohui.verb.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_xh_order")
public class Order {

    private Integer id;

    @TableField("order_code")
    private String orderCode;

    @TableField("user_name")
    private String userName;

    @TableField("sku_id")
    private Integer skuId;

    @TableField("sku_name")
    private String skuNme;

    private BigDecimal price;

    private BigDecimal quantity;

    @TableField("total_amount")
    private BigDecimal totalAmount;
}
