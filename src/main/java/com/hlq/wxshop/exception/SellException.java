package com.hlq.wxshop.exception;

import com.hlq.wxshop.enums.ResultEnum;

/**
 * 自定义异常类
 * @Author:HLQ
 * @Date:2019/3/11 20:24
 */
public class SellException extends  RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage() );
        this.code=resultEnum.getCode();
    }

    public SellException(Integer code,String messsage) {
        super(messsage);
        this.code = code;
    }
}
