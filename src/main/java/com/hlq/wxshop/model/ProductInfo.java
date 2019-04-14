package com.hlq.wxshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:HLQ
 * @Date:2019/3/6 11:12
 */
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;

    private  String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    /**
     * 销量
     */
    private Integer productVolume=0;

    /**
     * 点击量
     */
    private Integer productHits=0;

    private String productDescription;

    private String productImg;

    /**
     * 商品状态 0 上架  1 下架
     */
    private Integer productStatus=0;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "categoryType",insertable = false,updatable = false)
    private ProductCategory category;

    public ProductInfo() {
    }

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, Integer productVolume, String productDescription, String productImg, Integer productStatus, Integer categoryType, Date createTime, Date updateTime) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productVolume = productVolume;
        this.productDescription = productDescription;
        this.productImg = productImg;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
