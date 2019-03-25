package com.hlq.wxshop.utils;


import org.springframework.util.DigestUtils;

/**
 * MD5加密工具
 * @Author:HLQ
 * @Date:2019/3/25 17:35
 */
public class MD5Util {
    /**
     * 加密
     * @param text
     * @return
     */
    public static String md5(String text){
       //加密后的字符串
        String encodeStr=DigestUtils.md5DigestAsHex(text.getBytes());
        return encodeStr;
    }
}
