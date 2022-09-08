package com.xiaohui.verb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohui.verb.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
