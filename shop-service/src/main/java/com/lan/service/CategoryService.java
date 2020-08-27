package com.lan.service;

import com.lan.pojo.Category;

import java.util.List;

/**
 * @author xuminghao
 * @create 2020-08-27 8:18
 */
public interface CategoryService {
    List<Category>  queryAllRootLevelCat();
}
