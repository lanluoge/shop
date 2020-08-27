package com.lan.controller;

import com.lan.enums.YesOrNo;
import com.lan.pojo.Carousel;
import com.lan.pojo.Category;
import com.lan.service.CarouselService;
import com.lan.service.CategoryService;
import com.lan.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @Autowired
    CategoryService categoryService;
    @GetMapping("/carousel")
    @ApiOperation(value = "查询主页轮播图",notes = "查询主页轮播图",httpMethod = "GET")
    public JSONResult  carousel(){
        List<Carousel> res = carouselService.queryAll(YesOrNo.Yes.type);
        return JSONResult.ok(res);
    }

    @GetMapping("/cats")
    @ApiOperation(value = "查询父级目录",notes = "查询父级目录",httpMethod = "GET")
    public JSONResult  cat(){
        List<Category> categories = categoryService.queryAllRootLevelCat();
        return JSONResult.ok(categories);
    }
}
