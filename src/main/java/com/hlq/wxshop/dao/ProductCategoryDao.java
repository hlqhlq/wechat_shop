package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/4 17:44
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    /**
     * 根据类目类型查询
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 根据类目类型和类目状态查询
     * @param code
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryStatusAndCategoryTypeIn(Integer code,List<Integer> categoryTypeList);
    /**
     * 根据类目状态查询
     * @param code
     * @return
     */
    List<ProductCategory> findByCategoryStatus(Integer code);
}
