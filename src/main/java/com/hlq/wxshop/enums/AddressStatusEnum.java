package com.hlq.wxshop.enums;

import lombok.Getter;

/**
 * @Author:HLQ
 * @Date:2019/3/25 22:24
 */
@Getter
public enum AddressStatusEnum implements CodeEnum{

    isDefault(1,"默认地址"),
    notDefault(0,"不是默认地址"),
    ;
    private Integer code;
    private String message;

    AddressStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
