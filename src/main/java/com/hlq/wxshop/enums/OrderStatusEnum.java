package com.hlq.wxshop.enums;

import lombok.Getter;

/**
 * 订单状态
 * @Author:HLQ
 * @Date:2019/3/11 17:23
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
    ;
    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
