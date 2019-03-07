package com.hlq.wxshop.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**封装返回的必要商品信息，避免返回全部信息，不安全（比如库存）
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
    private String productImg;

    @JsonProperty("createTime")
    private Date createTime;

    @JsonProperty("updateTime")
    private Date updateTime;
}
