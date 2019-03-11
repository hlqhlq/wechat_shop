package com.hlq.wxshop.enums;

import lombok.Getter;

/**
 * 支付状态
 * @Author:HLQ
 * @Date:2019/3/11 17:25
 */
@Getter
public enum PayStatusEnum implements CodeEnum{

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
