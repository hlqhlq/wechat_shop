package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

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


    /**
     * 商品销量排行
     * @param limit
     * @return
     */
    @Query(nativeQuery = true,value = "select * from product_info where product_status=0 order by product_volume desc limit ?1")
    List<ProductInfo> findGoodsByVolume(Integer limit);


    /**
     * 商品浏览量排序
     * @param limit
     * @return
     */
    @Query(nativeQuery = true,value = "select * from product_info where  product_status=0 order by product_hits desc limit ?1")
    List<ProductInfo> findMostHotsGoodsByHits(Integer limit);


}
