package com.lan.controller;

import com.lan.bo.ShopcartBO;
import com.lan.utils.JSONResult;
import com.lan.utils.JsonUtils;
import com.lan.utils.RedisOperator;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuminghao
 * @create 2020-09-10 15:14
 */
@RestController
@RequestMapping("/shopcart")
public class ShopCatController extends  BaseController{

    @Autowired
    private RedisOperator redisOperator;


    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("");
        }

        System.out.println(shopcartBO);

        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        //需要判断有没有已经存在，如果已经存在需要累加数量
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART + ":" + userId);
        List<ShopcartBO> shopcartList=null;
        if (StringUtils.isNotBlank(shopcartJson)){
            boolean isHaving=false;
            shopcartList = JsonUtils.jsonToList(shopcartJson, ShopcartBO.class);
            for (ShopcartBO sc : shopcartList) {
                String specId = sc.getSpecId();
                if (specId.equals(shopcartBO.getSpecId())){
                    isHaving=true;
                    sc.setBuyCounts(sc.getBuyCounts()+shopcartBO.getBuyCounts());
                }
            }
            if (!isHaving){
                shopcartList.add(shopcartBO);
            }
        }else {
            shopcartList=new ArrayList<>();
            shopcartList.add(shopcartBO);
        }

        //覆盖
        redisOperator.set(FOODIE_SHOPCART+":"+userId,JsonUtils.objectToJson(shopcartList));

        return JSONResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public JSONResult del(
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return JSONResult.errorMsg("参数不能为空");
        }

        // TODO 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的商品

        return JSONResult.ok();
    }
}
