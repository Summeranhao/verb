package com.xiaohui.verb.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_xh_sku")
public class Sku {

    private Integer id;

    @TableField("sku_name")
    private String skuName;

    @TableField("sku_code")
    private String skuCode;

    private BigDecimal price;
}
