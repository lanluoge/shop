package com.lan.controller;

import com.lan.bo.UserBO;
import com.lan.pojo.Users;
import com.lan.service.UsersService;
import com.lan.utils.CookieUtils;
import com.lan.utils.JSONResult;
import com.lan.utils.JsonUtils;
import com.lan.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录",tags = "注册登录")
@RestController
@RequestMapping("/passport")
public class PassportController {
    @Autowired
    UsersService usersService;
    @GetMapping("/queryUsernameIsExist")
    @ApiOperation(value = "查询用户名是否存在",notes = "查询用户名是否存在",httpMethod = "GET")
    public JSONResult queryUsernameIsExist(String username){
        if (StringUtils.isBlank(username)){
            return JSONResult.errorMsg("用户名不能为空");
        }
        if (!usersService.queryUsernameIsExist(username)){
            return JSONResult.errorMsg("用户名不存在");
        }
        return JSONResult.ok();
    }

    @PostMapping("/regist")
    @ApiOperation(value = "创建用户",notes = "创建用户",httpMethod = "POST")
    public JSONResult createUser(@RequestBody UserBO userBO){

        //1.用户名 密码不能为空
        if(StringUtils.isBlank(userBO.getUsername())||StringUtils.isBlank(userBO.getPassword())||StringUtils.isBlank(userBO.getConfirmPassword())){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        //2.用户名不能存在
        if (usersService.queryUsernameIsExist(userBO.getUsername())){
            return JSONResult.errorMsg("用户名已经存在");
        }

        //3.密码和确认密码必须相同
        if (!userBO.getConfirmPassword().equals(userBO.getPassword())){
            return JSONResult.errorMsg("两次密码输入不一致");
        }
        //4.注册用户
        usersService.createUser(userBO);
        return JSONResult.ok();
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录",notes = "登录",httpMethod = "POST")
    public JSONResult login(@RequestBody UserBO userBO,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        //1.用户名 密码不能为空
        String username=userBO.getUsername();
        String password=userBO.getPassword();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        Users usersRes = usersService.queryUserForlogin(username, MD5Utils.getMD5Str(password));
        if (usersRes==null){
            return JSONResult.errorMsg("用户名或密码错误");
        }
        usersRes=setNullProperty(usersRes);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(usersRes),true);
        return JSONResult.ok(usersRes);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出账号",notes = "退出并清除cookie",httpMethod = "GET")
    public JSONResult logout(HttpServletRequest request,HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "user");
        return JSONResult.ok();
    }


    private Users setNullProperty(Users usersRes){
        usersRes.setPassword(null);
        usersRes.setRealname(null);
        usersRes.setUpdatedTime(null);
        usersRes.setCreatedTime(null);
        usersRes.setEmail(null);
        usersRes.setBirthday(null);
        return usersRes;
    }

}
