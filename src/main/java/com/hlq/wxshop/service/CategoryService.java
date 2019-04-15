package com.hlq.wxshop.service;

import com.hlq.wxshop.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/6 10:51
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryType);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    List<ProductCategory> findByStatusCategoryTypeIn(Integer code,List<Integer> categoryTypeList);

    /**
     * 新增或修改
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * 根据类目状态查询
     * @param code
     * @return
     */
    List<ProductCategory> findByStatus(Integer code);

    /**
     * 分页查询类目列表
     * @param pageable
     * @return
     */
    Page<ProductCategory> findAll(Pageable pageable);

    /**
     * 开启类目状态
     * @param categoryId
     * @return
     */
    ProductCategory putOn(Integer categoryId);

    /**
     * 禁用类目状态
     * @param categoryId
     * @return
     */
    ProductCategory takeOff(Integer categoryId);
}
