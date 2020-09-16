package com.lan.controller;

import com.lan.pojo.Items;
import com.lan.pojo.ItemsImg;
import com.lan.pojo.ItemsParam;
import com.lan.pojo.ItemsSpec;
import com.lan.service.ItemService;
import com.lan.utils.JSONResult;
import com.lan.utils.PagedGridResult;
import com.lan.vo.ItemCommentsLevelCountVO;
import com.lan.vo.ItemsInfoVO;
import com.lan.vo.ShopcartVO;
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
public class ItemsContrlloer extends BaseController{
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

    @ApiOperation(value = "查询商品用户评价详情",notes = "查询商品用户评价详情",httpMethod = "GET")
    @RequestMapping("/comments")
    public JSONResult comments(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String  itemId,
            @ApiParam(name = "page",value = "页数")
            @RequestParam   Integer page,
            @ApiParam(name = "level",value = "评论等级")
            @RequestParam   Integer level,
            @ApiParam(name = "pageSize",value = "每页评论显示数量")
            @RequestParam Integer  pageSize
                                        ){

        if (StringUtils.isBlank(itemId)){
            return JSONResult.errorMsg("商品id不能为空");
        }
        if (page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize=COMMON_PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.queryComments(itemId, level, page, pageSize);

        return  JSONResult.ok(pagedGridResult);
    }

    @ApiOperation(value = "使用关键字查询商品",notes = "使用关键字查询商品",httpMethod = "GET")
    @RequestMapping("/search")
    public JSONResult search( @ApiParam(name = "sort",value = "排序类型",required = true)
                            @RequestParam String  sort,
                            @ApiParam(name = "keywords",value = "查询关键字",required = true)
                            @RequestParam String  keywords,
                            @ApiParam(name = "page",value = "页数")
                            @RequestParam   Integer page,
                            @ApiParam(name = "pageSize",value = "每页评论显示数量")
                            @RequestParam Integer  pageSize){

        if (StringUtils.isBlank(keywords)){
            return JSONResult.errorMsg(null);
        }

        if (page==null){
            page=1;
        }

        if (pageSize==null){
            pageSize=PAGE_SIZE;
        }

        PagedGridResult pagedGridResult = itemService.queryItemsByKeywords( keywords, sort,page, pageSize);
        return  JSONResult.ok(pagedGridResult);
    }


    @ApiOperation(value = "使用三级目录id查询商品",notes = "使用三级目录id查询商品",httpMethod = "GET")
    @RequestMapping("/catItems")
    public JSONResult catItems( @ApiParam(name = "sort",value = "排序类型",required = true)
                              @RequestParam String  sort,
                              @ApiParam(name = "catId",value = "查询关键字",required = true)
                              @RequestParam Integer catId,
                              @ApiParam(name = "page",value = "页数")
                              @RequestParam   Integer page,
                              @ApiParam(name = "pageSize",value = "每页评论显示数量")
                              @RequestParam Integer  pageSize){

        if (catId==null){
            return JSONResult.errorMsg(null);
        }
        if (page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize=PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.queryItemsByThirdCat(catId, sort,page, pageSize);

        return  JSONResult.ok(pagedGridResult);
    }


    @ApiOperation(value = "根据商品规格ids查找最新的商品数据",notes = "根据商品规格ids查找最新的商品数据",httpMethod = "GET")
    @RequestMapping("/refresh")
    public JSONResult refresh(
            @ApiParam(name = "itemSpecIds",value = "商品id",required = true)
            @RequestParam String  itemSpecIds){

        if (StringUtils.isBlank(itemSpecIds)){
            return JSONResult.ok();
        }
        List<ShopcartVO> itemsSpecs = itemService.queryItemsBySpecIds(itemSpecIds);
        return  JSONResult.ok(itemsSpecs);
    }
}
