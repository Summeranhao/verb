package com.xiaohui.verb.controller.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohui.verb.domain.User;
import com.xiaohui.verb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 多个查询(分页）
    @GetMapping("/selectAllPageQuery")
    public IPage<User> selectAllPageQuery(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum,
                                          @RequestParam("pageSize") int pageSize) {
        return userService.selectAllPageQuery(name,pageNum,pageSize);

    }


    @GetMapping ("/queryAll")
    @ResponseBody
    public String getUserList(){
        List<User> users =userService.queryUser(null);
        return users.toString();
    }

    @GetMapping("/query")
    @ResponseBody
    public String getUser(String userName){

        User user=new User();
        user.setUsername(userName);
        List<User> users =userService.queryUser(user);
        return users.toString();
    }

    @PostMapping("/del")
    @ResponseBody
    public String deleteUser(Integer id){
       userService.deleteUser( id);
        return "删除成功！";
    }


    @PostMapping("/edit")
    @ResponseBody
    public String editUser(User user){
        userService.editUser( user);
        return "编辑成功！";
    }


    @PostMapping("/add")
    @ResponseBody
    public String addUser(User user){
        userService.addUser( user);
        return "新增成功！";
    }
}
