package com.lan.service.impl;

import com.lan.mapper.StuMapper;
import com.lan.pojo.Stu;
import com.lan.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class StuServiceImpl implements StuService {
    @Autowired
    public StuMapper stuMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveChildren() {
        saveChild1();
//        int a = 1 / 0;
        saveChild2();
    }
//    @Transactional(propagation = Propagation.REQUIRED)
    public void saveParent() {
        Stu stu = new Stu();
        stu.setName("parent");
        stu.setAge(19);
        stuMapper.insert(stu);
    }
//    @Transactional(propagation = Propagation.REQUIRED)
    public void saveChild1() {
        Stu stu1 = new Stu();
        stu1.setName("child-1");
        stu1.setAge(11);
        stuMapper.insert(stu1);
    }
//    @Transactional(propagation = Propagation.REQUIRED)
    public void saveChild2() {
        Stu stu2 = new Stu();
        stu2.setName("child-2");
        stu2.setAge(22);
        stuMapper.insert(stu2);
    }
}
