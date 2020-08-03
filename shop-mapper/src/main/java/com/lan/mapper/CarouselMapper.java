package com.lan.mapper;

import com.lan.my.mapper.MyMapper;
import com.lan.pojo.Carousel;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public interface CarouselMapper extends MyMapper<Carousel> {
}