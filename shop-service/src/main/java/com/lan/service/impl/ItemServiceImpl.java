package com.lan.service.impl;

import com.lan.enums.CommentsLevel;
import com.lan.mapper.*;
import com.lan.pojo.*;
import com.lan.service.ItemService;
import com.lan.vo.ItemCommentsLevelCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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
}
