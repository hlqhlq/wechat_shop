package com.hlq.wxshop.utils;

/**
 * @Author:HLQ
 * @Date:2019/3/16 0:22
 */
public class Test {

    public static void main(String[] args) {
        String webPath ="\\"+"upload"+"\\"+"ddd";
        System.out.println(webPath);
        webPath = webPath.replaceAll("\\\\", "/");
        System.out.println(webPath);
     }


}
