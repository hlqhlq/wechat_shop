package com.hlq.wxshop.VO;

import lombok.Data;

/**
 * http请求返回的结果
 * @Author:HLQ
 * @Date:2019/3/6 15:18
 */
@Data
public class ResultVO<T>{
    /**
     * 提示码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
