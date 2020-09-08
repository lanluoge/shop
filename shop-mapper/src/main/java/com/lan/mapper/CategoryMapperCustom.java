package com.lan.mapper;


import com.lan.vo.CategoryVO;
import com.lan.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom  {

     List<CategoryVO>  getSubCatList(Integer  fatherId);
     List<NewItemsVO>  getNewSixItemsLazy(@Param("paramsMap") Map map);
}