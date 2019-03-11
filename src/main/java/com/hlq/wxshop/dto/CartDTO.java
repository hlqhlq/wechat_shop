package com.hlq.wxshop.dto;

import lombok.Data;

/**
 * 购物车
 * @Author:HLQ
 * @Date:2019/3/11 21:09
 */
@Data
public class CartDTO {
    /**
     * 商品id
     */
    private  String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
