package com.lan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lan.enums.CommentsLevel;
import com.lan.mapper.*;
import com.lan.pojo.*;
import com.lan.service.ItemService;
import com.lan.utils.DesensitizationUtil;
import com.lan.utils.PagedGridResult;
import com.lan.vo.ItemCommentVO;
import com.lan.vo.ItemCommentsLevelCountVO;
import com.lan.vo.SearchItemsVO;
import com.lan.vo.ShopcartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service


public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemsMapper itemsMapper;

    @Autowired
    ItemsImgMapper itemsImgMapper;

    @Autowired
    ItemsSpecMapper itemsSpecMapper;

    @Autowired
    ItemsParamMapper itemsParamMapper;

    @Autowired
    ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    ItemsMapperCustom itemsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryByItemsId(String  itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemsImgsList(String  itemId) {
        Example example=new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemsSpecList(String  itemId) {
        Example example=new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String  itemId) {
        Example example=new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemCommentsLevelCountVO queryCommentCounts(String itemId) {
        Integer goodCount = getCommentsCount(itemId,CommentsLevel.GOOD.type);
        Integer badCount = getCommentsCount(itemId,CommentsLevel.BAD.type);
        Integer normalCount = getCommentsCount(itemId,CommentsLevel.NORMAL.type);
        Integer  totalCount = goodCount + badCount + normalCount;

        ItemCommentsLevelCountVO itemCommentsLevelCountVO = new ItemCommentsLevelCountVO();
        itemCommentsLevelCountVO.setBadCounts(badCount);
        itemCommentsLevelCountVO.setGoodCounts(goodCount);
        itemCommentsLevelCountVO.setNormalCounts(normalCount);
        itemCommentsLevelCountVO.setTotalCounts(totalCount);
        return itemCommentsLevelCountVO;
    }



    Integer getCommentsCount(String  itemId,Integer level){
        ItemsComments condition=new ItemsComments();
        condition.setItemId(itemId);
        if (level!=null){
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryComments(String itemId,
                                         Integer level,
                                         Integer page,
                                         Integer pageSize) {
        HashMap<String,Object> map=new HashMap();
        map.put("itemId",itemId);
        map.put("level",level);
        /**
         * page: 第几页
         * pageSize: 每页显示条数
         */
        PageHelper.startPage(page, pageSize);
//        4.分页数据封装到 PagedGridResult.java 传给前端
        List<ItemCommentVO> list = itemsMapperCustom.queryItemsComments(map);

        for (ItemCommentVO itemCommentVO : list) {
            itemCommentVO.setNickname(DesensitizationUtil.commonDisplay(itemCommentVO.getNickname()));
        }
        return setGridResult(page,list);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryItemsByKeywords(String keywords, String sort, Integer page, Integer pageSize) {
        HashMap<String, Object> param= new HashMap<>();
        param.put("keywords",keywords);
        param.put("sort",sort);
        PageHelper.startPage(page,pageSize);
        List<SearchItemsVO> list = itemsMapperCustom.queryItemsByKeyword(param);
        return setGridResult(page,list);
    }

    @Transactional(propagation = Propagation.SUPPORTS)

    @Override
    public PagedGridResult queryItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize) {
        HashMap<String, Object> param= new HashMap<>();
        param.put("catId",catId);
        param.put("sort",sort);
        PageHelper.startPage(page,pageSize);
        List<SearchItemsVO> list = itemsMapperCustom.queryItemsByThirdCat(param);
        return setGridResult(page,list);
    }

    @Override
    public List<ShopcartVO> queryItemsBySpecIds(String specIds) {
        String[] split = specIds.split(",");
        ArrayList<String>  list=new ArrayList<>();
        Collections.addAll(list,split);
        return  itemsMapperCustom.queryItemsBySpecIds(list);
    }


    public PagedGridResult setGridResult(Integer page,List list){
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return  grid;
    }
}
