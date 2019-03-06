package com.hlq.wxshop.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:HLQ
 * @Date:2019/3/6 17:25
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("desc")
    private String productDescription;

    @JsonProperty("img")
    private String productIcon;
}
