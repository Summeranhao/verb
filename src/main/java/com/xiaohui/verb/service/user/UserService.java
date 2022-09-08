package com.xiaohui.verb.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaohui.verb.domain.User;
import com.xiaohui.verb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

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
}
