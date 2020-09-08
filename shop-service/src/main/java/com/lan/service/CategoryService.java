package com.lan.service;

import com.lan.pojo.Category;
import com.lan.vo.CategoryVO;

import java.util.List;

/**
 * @author xuminghao
 * @create 2020-08-27 8:18
 */
public interface CategoryService {
    /**
     *查询所有一级分类
     * @return
     */
    List<Category>  queryAllRootLevelCat();

    /**
     * 根据一级分类id查询子目录
     * @return
     */
    List<CategoryVO>  getSubCatList(Integer rootFatherId);
}
