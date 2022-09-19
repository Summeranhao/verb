package com.xiaohui.verb.controller.user;

import com.xiaohui.verb.aspect.RequiredLog;
import com.xiaohui.verb.controller.common.BaseResponse;
import com.xiaohui.verb.domain.User;
import com.xiaohui.verb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @GetMapping(value = "/login")
    @RequiredLog("登录")
    public BaseResponse login(User user,HttpSession session){
        return userService.login(user, session);
    }

    // 多个查询(分页）
    @GetMapping("/selectAllPageQuery")
    public BaseResponse selectAllPageQuery(@RequestParam("name") String name, @RequestParam("pageNum") int pageNum,
                                          @RequestParam("pageSize") int pageSize) {
        return BaseResponse.ok(userService.selectAllPageQuery(name,pageNum,pageSize));

    }

    @GetMapping ("/queryAll")
    @ResponseBody
    @RequiredLog("用户列表查询")
    public BaseResponse getUserList(){

        List<User> users =userService.queryUser(null);
        return BaseResponse.ok(users);
    }

    @GetMapping("/query")
    @ResponseBody
    public BaseResponse getUser(String userName){

        User user=new User();
        user.setUsername(userName);
        List<User> users =userService.queryUser(user);
        return BaseResponse.ok(users);
    }

    @PostMapping("/del")
    @ResponseBody
    public BaseResponse deleteUser(Integer id){
       userService.deleteUser( id);
        return BaseResponse.ok(null);
    }


    @PostMapping("/edit")
    @ResponseBody
    public BaseResponse editUser(User user){
        userService.editUser( user);
        return BaseResponse.ok(null);
    }


    @PostMapping("/add")
    @ResponseBody
    public BaseResponse addUser(User user){
        userService.addUser( user);
        return BaseResponse.ok(null);
    }
}
