package com.lan.controller;

import com.lan.service.UsersService;
import com.lan.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "注册登录",tags = "注册登录")
@RestController
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
}
