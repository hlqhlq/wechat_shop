package com.hlq.wxshop.enums;

import lombok.Getter;

/**
 * @Author:HLQ
 * @Date:2019/4/16 17:38
 */
@Getter
public enum CarouselStatusEnum implements CodeEnum{

    ON(0,"开启状态"),
    OFF(1,"禁用"),
    ;

    private Integer code;
    private String message;

    CarouselStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
