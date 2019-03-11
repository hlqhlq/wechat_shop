package com.hlq.wxshop.service;

import com.hlq.wxshop.dto.CartDTO;
import com.hlq.wxshop.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/6 11:32
 */
public interface ProductInfoService {

   ProductInfo findOne(String productId);

    /**
     * 查询上架的商品
     * @return
     */
   List<ProductInfo> findOnAll();

    /**
     * 根据类目查询商品
     * @param categoryType
     * @return
     */
   List<ProductInfo> findByCategoryTypeIn(Integer categoryType);
    /**
     * 查询商品列表 分页
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 保存商品
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOList
     */
    void addStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decStock(List<CartDTO> cartDTOList);
}
