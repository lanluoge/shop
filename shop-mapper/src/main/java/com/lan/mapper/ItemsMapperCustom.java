package com.lan.mapper;

import com.lan.vo.ItemCommentVO;
import com.lan.vo.SearchItemsVO;
import com.lan.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom  {

    List<ItemCommentVO> queryItemsComments(@Param("paramsMap")Map<String,Object>  params);

    List<SearchItemsVO> queryItemsByKeyword(@Param("paramsMap")Map<String,Object>  params);
    List<SearchItemsVO> queryItemsByThirdCat(@Param("paramsMap")Map<String,Object>  params);
    List<ShopcartVO> queryItemsBySpecIds(@Param("specIdsList")List specIdsList);
    int decreaseItemSpecStock(@Param("specId") String specId,
                              @Param("pendingCounts") int pendingCounts);

}