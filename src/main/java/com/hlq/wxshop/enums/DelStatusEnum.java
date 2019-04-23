package com.hlq.wxshop.enums;

import lombok.Getter;

/**
 * @Author:HLQ
 * @Date:2019/4/20 15:29
 */
@Getter
public enum DelStatusEnum implements CodeEnum{
    FORMAL(0, "正常"),
    DELETE(1, "删除"),
    ;
    private Integer code;

    private String message;

    DelStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
