package com.lan.service.impl;

import com.lan.mapper.UsersMapper;
import com.lan.pojo.Users;
import com.lan.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersMapper  usersMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example example =new Example(Users.class);
        Example.Criteria userCri = example.createCriteria();
        userCri.andEqualTo("username",username);
        Users users = usersMapper.selectOneByExample(example);
        if (users==null){
            return false;
        }else {
            return true;
        }
    }
}
