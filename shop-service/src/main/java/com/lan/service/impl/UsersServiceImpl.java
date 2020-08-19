package com.lan.service.impl;

import com.lan.bo.UserBO;
import com.lan.enums.Sex;
import com.lan.mapper.UsersMapper;
import com.lan.pojo.Users;
import com.lan.service.UsersService;
import com.lan.utils.DateUtil;
import com.lan.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    Sid sid;


    @Autowired
    UsersMapper  usersMapper;

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";


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

    @Override
    public Users createUser(UserBO userbo) {
        String id = sid.nextShort();
        Users users=new Users();
        users.setId(id);
        users.setUsername(userbo.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(userbo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        users.setNickname(userbo.getUsername());
        users.setBirthday(DateUtil.stringToDate("1999-01-02"));
        users.setFace(USER_FACE);
        users.setSex(Sex.man.type);
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        usersMapper.insert(users);
        return null;
    }

    @Override
    public Users queryUserForlogin(String username, String password) {
        Example example =new Example(Users.class);
        Example.Criteria userCri = example.createCriteria();
        userCri.andEqualTo("username",username);
        userCri.andEqualTo("password",password);
        Users res = usersMapper.selectOneByExample(example);
        return res;
    }
}
