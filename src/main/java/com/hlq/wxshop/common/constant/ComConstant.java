package com.hlq.wxshop.common.constant;

import java.io.File;

/**
 * 公共常量
 * @Author:HLQ
 * @Date:2019/4/13 20:43
 */
public class ComConstant {

    /**
     * 文件根路径虚拟地址
     */
    public static final String FILEADDR = "/files/**";
    /**
     * 文件根路径虚拟路径
     */
    public static final String FILESPATH = File.separator + "files";
    /**
     * 上传文件路径
     */
    public static final String UPLOADPATH = File.separator + "upload" + File.separator;
    /**
     * 上传图片路径
     */
    public static final String IMAGEPATH = UPLOADPATH + "image" + File.separator;
}
