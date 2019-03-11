package com.hlq.wxshop.enums;

import lombok.Getter;

/**
 * 商品状态
 * @Author:HLQ
 * @Date:2019/3/6 11:39
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{

    On(0,"上架"),
    Down(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum() {
    }

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
