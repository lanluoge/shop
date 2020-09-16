package com.lan.service;

import com.lan.pojo.Items;
import com.lan.pojo.ItemsImg;
import com.lan.pojo.ItemsParam;
import com.lan.pojo.ItemsSpec;
import com.lan.utils.PagedGridResult;
import com.lan.vo.ItemCommentsLevelCountVO;
import com.lan.vo.ShopcartVO;

import java.util.List;

public interface ItemService {

    /**
     *  根据商品id查询商品详情
     * @param itemId
     * @return
     */
    Items  queryByItemsId(String  itemId);

    /**
     *  根据商品id查询商品图片
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemsImgsList(String  itemId);

    /**
     *  根据商品id查询商品规格
     * @param itemId
     * @return
     */
    List<ItemsSpec>  queryItemsSpecList(String  itemId);

    /**
     *  根据商品id查询商品参数
     * @param itemId
     * @return
     */
    ItemsParam  queryItemParam(String  itemId);

    /**
     *  根据商品id查询商品的评价等级数量
     * @param itemId
     * @return
     */
    ItemCommentsLevelCountVO queryCommentCounts(String  itemId);


    /**
     *  根据商品id查询商品的评价
     * @param itemId
     * @return
     */
    PagedGridResult queryComments(String  itemId, Integer level, Integer page, Integer pageSize);

    /**
     *  根据关键字查询商品
     * @param keywords
     * @return
     */
    PagedGridResult queryItemsByKeywords(String  keywords, String sort, Integer page, Integer pageSize);


    /**
     *  根据三级目录id查询商品
     * @param catId
     * @return
     */
    PagedGridResult queryItemsByThirdCat(Integer  catId, String sort, Integer page, Integer pageSize);


    /**
     *  根据规格id查询最新购物车中的商品数据（用于渲染购物车）
     * @param specIds
     * @return
     */
    List<ShopcartVO> queryItemsBySpecIds(String specIds);


    /**
     * 根据商品规格id获取规格对象的具体信息
     * @param specId
     * @return
     */
    public ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品id获得商品图片主图url
     * @param itemId
     * @return
     */
    public String queryItemMainImgById(String itemId);

    /**
     * 减少库存
     * @param specId
     * @param buyCounts
     */
    public void decreaseItemSpecStock(String specId, int buyCounts);

}
