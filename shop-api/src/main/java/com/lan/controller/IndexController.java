package com.lan.controller;

import com.lan.enums.YesOrNo;
import com.lan.pojo.Carousel;
import com.lan.service.CarouselService;
import com.lan.utils.JSONResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("index")
@Api(value = "首页",tags = "首页相关的接口")
public class IndexController {

    @Autowired
    CarouselService carouselService;
    @GetMapping("/carousel")
    public JSONResult  test(){
        List<Carousel> res = carouselService.queryAll(YesOrNo.Yes.type);
        return JSONResult.ok(res);
    }
}
