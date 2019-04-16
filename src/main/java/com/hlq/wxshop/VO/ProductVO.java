package com.hlq.wxshop.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/6 15:39
 */
@Data
public class ProductVO {

    @JsonProperty("cname")  //在json中的别名
   private String categoryName;

    @JsonProperty("ctype")
    private Integer categoryType;

    @JsonProperty("cimage")
    private String categoryIco;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
