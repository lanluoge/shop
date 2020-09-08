package com.lan.mapper;


import com.lan.vo.CategoryVO;
import java.util.List;

public interface CategoryMapperCustom  {

     List<CategoryVO>  getSubCatList(Integer  fatherId);
}