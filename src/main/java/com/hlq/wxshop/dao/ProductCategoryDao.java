package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:HLQ
 * @Date:2019/3/4 17:44
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

}
