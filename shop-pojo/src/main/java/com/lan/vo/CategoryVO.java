package com.lan.vo;

import java.util.List;

/**
 * @author xuminghao
 * @create 2020-08-27 13:00
 */
public class CategoryVO {
    private  Integer id;
    private String  name;
    private String type;
    private Integer fatherId;
    private List<SubCategoryVO> subCatList;
}
