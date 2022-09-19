package com.xiaohui.verb.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**日志实体*/
@Data
@Accessors(chain = true)
@TableName("t_xh_log")
public class Log {
    /**主键*/
    private Integer id;
    /**执行人*/
    private String executor;
    /**方法描述*/
    private String operation;
    /**执行方法*/
    private String method;
    /**执行时长*/
    @TableField("execution_duration")
    private Long executionDuration;
    /**ip*/
    private String ip;
    /**创建时间*/
    private Date createtime;
}

