package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.dao.ProductCategoryDao;
import com.hlq.wxshop.model.ProductCategory;
import com.hlq.wxshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/6 10:55
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao dao;
    @Override
    public ProductCategory findOne(Integer categoryType) {
        return dao.findOne(categoryType);
    }

    @Override
    public List<ProductCategory> findAll() {
        return dao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return dao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public ProductCategory save(ProductCategory productCategory) {
        return dao.save(productCategory);
    }
}
