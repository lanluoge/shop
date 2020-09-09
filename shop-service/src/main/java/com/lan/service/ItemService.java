package com.lan.service;

import com.lan.pojo.*;
import com.lan.vo.ItemCommentsLevelCountVO;

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

}
