package com.hlq.wxshop.utils;

import java.util.Random;

/**
 * 生成主键工具类
 * @Author:HLQ
 * @Date:2019/3/11 20:48
 */
public class KeyUtil {

    //多线程

    /**
     * 时间+随机数  主键
     * @return
     */
    public static synchronized String UniqueKey(){

        Random random=new Random();
        //随机生成6位数
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
