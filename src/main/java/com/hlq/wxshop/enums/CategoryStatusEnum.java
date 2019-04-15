package com.hlq.wxshop.enums;

import lombok.Getter;

/**
 * @Author:HLQ
 * @Date:2019/4/15 21:40
 */
@Getter
public enum CategoryStatusEnum implements CodeEnum{

    ON(0,"开启状态"),
    OFF(1,"禁用"),
    ;

    private Integer code;
    private String message;

    CategoryStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
