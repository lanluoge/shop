package com.lan.controller;

import com.lan.enums.YesOrNo;
import com.lan.pojo.Carousel;
import com.lan.pojo.Category;
import com.lan.service.CarouselService;
import com.lan.service.CategoryService;
import com.lan.utils.JSONResult;
import com.lan.utils.JsonUtils;
import com.lan.utils.RedisOperator;
import com.lan.vo.CategoryVO;
import com.lan.vo.NewItemsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("index")
@Api(value = "首页",tags = "首页相关的接口")
public class IndexController {

    @Autowired
    CarouselService carouselService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    RedisOperator redisOperator;


    @GetMapping("/carousel")
    @ApiOperation(value = "查询主页轮播图",notes = "查询主页轮播图",httpMethod = "GET")
    public JSONResult  carousel(){
//        List<Carousel> res = carouselService.queryAll(YesOrNo.Yes.type);
        List<Carousel>  list=new ArrayList<>();
        String carouselStr = redisOperator.get("carousel");
        if (StringUtils.isBlank(carouselStr)){
            list=carouselService.queryAll(YesOrNo.Yes.type);;
            redisOperator.set("carousel", JsonUtils.objectToJson(list));
        }else {
            list=JsonUtils.jsonToList(carouselStr,Carousel.class);
        }
        return JSONResult.ok(list);
    }

    @GetMapping("/cats")
    @ApiOperation(value = "查询父级目录",notes = "查询父级目录",httpMethod = "GET")
    public JSONResult  cat(){
        List<Category> categories = categoryService.queryAllRootLevelCat();
        return JSONResult.ok(categories);
    }

    @GetMapping("/subCat/{FatherId}")
    @ApiOperation(value = "查询商品子分类",notes = "查询父级目录",httpMethod = "GET")
    public JSONResult  subCat(@PathVariable Integer FatherId){
        if (FatherId==null){
            return JSONResult.errorMsg("父ID不能为空");
        }
        List<CategoryVO> subCatList = categoryService.getSubCatList(FatherId);
        return JSONResult.ok(subCatList);
    }


    @GetMapping("/sixNewItems/{rootCatId}")
    @ApiOperation(value = "查询一级分类下最新6条数据",notes = "查询一级分类下最新6条数据",httpMethod = "GET")
    public JSONResult  sixNewItems(@PathVariable Integer rootCatId){
        if (rootCatId==null){
            return JSONResult.errorMsg("分类不存在");
        }
        List<NewItemsVO> subCatList = categoryService.getSixNewItemsLazy(rootCatId);
        return JSONResult.ok(subCatList);
    }
}
