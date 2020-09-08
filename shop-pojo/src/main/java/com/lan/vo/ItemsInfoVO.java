package com.lan.vo;

import com.lan.pojo.Items;
import com.lan.pojo.ItemsImg;
import com.lan.pojo.ItemsParam;
import com.lan.pojo.ItemsSpec;

import java.util.List;

/**
 * @author xuminghao
 * @create 2020-09-08 16:43
 */
public class ItemsInfoVO {
    public Items item;
    public List<ItemsImg> itemImgList;
    public List<ItemsSpec> itemSpecList;
    public ItemsParam itemParams;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }

    public ItemsParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemsParam itemParams) {
        this.itemParams = itemParams;
    }
}
