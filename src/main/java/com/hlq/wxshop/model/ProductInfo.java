package com.hlq.wxshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
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

    private String productDescription;

    private String productImg;

    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductInfo() {
    }

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productImg, Integer productStatus, Integer categoryType, Date createTime, Date updateTime) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productImg = productImg;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
