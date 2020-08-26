package com.lan.service;

import com.lan.pojo.Carousel;

import java.util.List;

/**
 * @author xuminghao
 * @create 2020-08-26 17:01
 */
public interface CarouselService  {
    List<Carousel>  queryAll(Integer isShow);
}
