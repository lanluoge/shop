package com.lan.service;

import com.lan.bo.UserBO;
import com.lan.pojo.Users;

public interface UsersService {
    boolean  queryUsernameIsExist(String username);
    Users createUser(UserBO userbo);
    Users queryUserForlogin(String username, String password);
}
