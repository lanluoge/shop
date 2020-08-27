package com.lan.mapper;


import com.lan.pojo.Category;
import io.swagger.models.auth.In;

import java.util.List;

public interface CategoryMapperCustom  {

     List<Category>  getSubCatList(Integer  fatherId);
}