package com.hlq.wxshop.service;

import com.hlq.wxshop.model.ProductCategory;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/6 10:51
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryType);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增或修改
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
