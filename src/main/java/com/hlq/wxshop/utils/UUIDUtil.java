package com.hlq.wxshop.utils;

import java.util.UUID;

/**
 * @Author:HLQ
 * @Date:2019/4/13 15:56
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
