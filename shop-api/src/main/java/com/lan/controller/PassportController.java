package com.lan.controller;

import com.lan.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassportController {
    @Autowired
    UsersService usersService;
    @GetMapping("/queryUsernameIsExist")
    public int  queryUsernameIsExist(String username){
        if (StringUtils.isBlank(username)){
            return 500;
        }
        if (!usersService.queryUsernameIsExist(username)){
            return 500;
        }
        return 200;
    }
}
