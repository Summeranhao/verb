package com.xiaohui.verb.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_xh_user")
public class User {

    private Integer id;

    private String username;

    private String password;

    private String name;
}
