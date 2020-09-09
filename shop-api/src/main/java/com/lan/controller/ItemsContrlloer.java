package com.lan.controller;

import com.lan.pojo.Items;
import com.lan.pojo.ItemsImg;
import com.lan.pojo.ItemsParam;
import com.lan.pojo.ItemsSpec;
import com.lan.service.ItemService;
import com.lan.utils.JSONResult;
import com.lan.vo.ItemCommentsLevelCountVO;
import com.lan.vo.ItemsInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xuminghao
 * @create 2020-09-08 16:49
 */
@Api(value = "商品接口",tags = {"商品展示相关接口"})
@RestController
@RequestMapping("/items")
public class ItemsContrlloer {
    @Autowired
    ItemService itemService;

    @ApiOperation(value = "查询商品详情",notes = "查询商品详情",httpMethod = "GET")
    @RequestMapping("/info/{itemId}")
    public JSONResult info(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @PathVariable String  itemId){

        if (StringUtils.isBlank(itemId)){
            return JSONResult.errorMsg("商品id不能为空");
        }

        Items items = itemService.queryByItemsId(itemId);
        List<ItemsImg> itemsImgs = itemService.queryItemsImgsList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemsSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemsInfoVO itemsInfoVO = new ItemsInfoVO();
        itemsInfoVO.setItem(items);
        itemsInfoVO.setItemImgList(itemsImgs);
        itemsInfoVO.setItemSpecList(itemsSpecs);
        itemsInfoVO.setItemParams(itemsParam);
        return  JSONResult.ok(itemsInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级",notes = "查询商品评价等级",httpMethod = "GET")
    @RequestMapping("/commentLevel")
    public JSONResult commentLevel(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String  itemId){

        if (StringUtils.isBlank(itemId)){
            return JSONResult.errorMsg("商品id不能为空");
        }

        ItemCommentsLevelCountVO itemCommentsLevelCountVO = itemService.queryCommentCounts(itemId);

        return  JSONResult.ok(itemCommentsLevelCountVO);
    }
}
