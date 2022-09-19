package com.xiaohui.verb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohui.verb.domain.Log;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wanghui
* @description 针对表【t_xh_log】的数据库操作Mapper
* @createDate 2022-09-19 15:23:02
* @Entity generator.domain.TXhLog
*/
@Mapper
public interface TXhLogMapper extends BaseMapper<Log> {


}
