package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.dao.ProductCategoryDao;
import com.hlq.wxshop.enums.CategoryStatusEnum;
import com.hlq.wxshop.model.ProductCategory;
import com.hlq.wxshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Override
    public List<ProductCategory> findByStatus(Integer code) {
        return dao.findByCategoryStatus(code);
    }

    @Override
    public List<ProductCategory> findByStatusCategoryTypeIn(Integer code,List<Integer> categoryTypeList) {
        return dao.findByCategoryStatusAndCategoryTypeIn(code,categoryTypeList);
    }

    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public ProductCategory putOn(Integer categoryId) {
        ProductCategory info = dao.findOne(categoryId);
        info.setCategoryStatus(CategoryStatusEnum.ON.getCode());
        info.setUpdateTime(new Date());
        return dao.save(info);
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public ProductCategory takeOff(Integer categoryId) {
        ProductCategory info = dao.findOne(categoryId);
        info.setCategoryStatus(CategoryStatusEnum.OFF.getCode());
        info.setUpdateTime(new Date());
        return dao.save(info);
    }
}
