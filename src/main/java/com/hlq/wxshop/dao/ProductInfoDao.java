package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    /**
     * 模糊查询
     * @param productId
     * @param productName
     * @param categoryType
     * @return
     */
    @Query(nativeQuery = true,value = "select * from product_info p where(p.product_id like CONCAT('%',?1,'%') or ?1 is null)"+
    "and (p.product_name like CONCAT('%',?2,'%') or ?2 is null)"+
    "and (p.category_type= ?3 or ?3 is null) order by ?#{#pageable}")
    Page<ProductInfo> searchByKey(String productId, String productName, Integer categoryType, Pageable pageable);



}
