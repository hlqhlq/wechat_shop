package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/6 11:18
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    /**
     * 查找上下架商品
     * @param status
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer status);

    /**
     * 根据类目查商品
     * @param categoryType
     * @return
     */
    List<ProductInfo> findByCategoryTypeIn(Integer categoryType);
}
