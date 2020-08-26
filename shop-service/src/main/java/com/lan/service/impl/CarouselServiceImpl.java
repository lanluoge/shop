package com.lan.service.impl;

import com.lan.mapper.CarouselMapper;
import com.lan.pojo.Carousel;
import com.lan.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author xuminghao
 * @create 2020-08-26 17:02
 */
@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    CarouselMapper carouselMapper;
    @Override
    public List<Carousel> queryAll(Integer isShow) {

        Example  example=new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow",isShow);
        List<Carousel> res = carouselMapper.selectByExample(example);
        return res;
    }
}
