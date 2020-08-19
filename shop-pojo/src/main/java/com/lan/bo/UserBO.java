package com.lan.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户bo对象",description = "注册时传入的对象")
public class UserBO {
    @ApiModelProperty(value = "用户名",name="用户名",example = "lanluo",required = true)
    private   String username;
    @ApiModelProperty(value = "密码",name="密码",example = "123456",required = true)
    private   String password;
    @ApiModelProperty(value = "确认密码",name="确认密码",example = "123456",required = true)
    private   String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
