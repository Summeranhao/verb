package com.xiaohui.verb.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaohui.verb.controller.common.BaseResponse;
import com.xiaohui.verb.domain.User;
import com.xiaohui.verb.interceptor.SessionInterceptor;
import com.xiaohui.verb.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);

    @Autowired
    private UserMapper userMapper;

    public List<User> queryUser(User user){

        QueryWrapper<User> queryWrapper = new  QueryWrapper<>();

        List<User> users = userMapper.selectList(queryWrapper);

        return users;

    }


    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    public void editUser(User user) {
        userMapper.updateById(user);
    }

    public void addUser(User user) {
        userMapper.insert(user);
    }

    public IPage<User> selectAllPageQuery(String name, int pageNum, int pageSize) {

        Page page=new Page();
        page.setPages(pageNum);
        page.setTotal(pageSize);

        QueryWrapper<User> queryWrapper = new  QueryWrapper<>();


        // 分页查询
        IPage iPage = userMapper.selectPage(page, queryWrapper);

        return iPage;

    }

    public BaseResponse login(User user, HttpSession session) {
        BaseResponse result = new BaseResponse();
        result.setFlag(false);
        result.setData(null);
        try {

            QueryWrapper<User> queryWrapper = new  QueryWrapper<>();
            queryWrapper.lambda().eq(StringUtils.isEmpty(user.getUsername()),User::getUsername,user.getUsername())
                    .eq(StringUtils.isEmpty(user.getPassword()),User::getPassword,user.getPassword());

            List<User> users = userMapper.selectList(queryWrapper);
            User userquery= CollectionUtils.isEmpty(users)?users.get(0):new User();
            Integer userId=userquery.getId();
            if(userId == null){
                result.setMsg("用户名或密码错误");
            }else{
                LOGGER.info("用户登陆成功--{}", userquery);
                // 登录成功之后将用户信息保存到session里
                session.setAttribute("user", userquery);
                result.setMsg("登录成功");
                result.setFlag(true);
                result.setData(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
