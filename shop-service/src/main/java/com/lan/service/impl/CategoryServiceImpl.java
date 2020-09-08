package com.lan.service.impl;

import com.lan.mapper.CategoryMapper;
import com.lan.mapper.CategoryMapperCustom;
import com.lan.pojo.Carousel;
import com.lan.pojo.Category;
import com.lan.service.CarouselService;
import com.lan.service.CategoryService;
import com.lan.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author xuminghao
 * @create 2020-08-27 8:19
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryMapperCustom categoryMapperCustom;

    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",1);
        List<Category> res = categoryMapper.selectByExample(example);
        return res;
    }

    @Override
    public List<CategoryVO> getSubCatList(Integer rootFatherId) {
        return categoryMapperCustom.getSubCatList(rootFatherId);
    }


}
