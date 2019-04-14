package com.hlq.wxshop.common.controller;

import com.hlq.wxshop.utils.HostIpUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author:HLQ
 * @Date:2019/4/13 16:55
 */
public abstract  class BaseController {

    /**
     * @Autowired标识的request线程安全
     */
    @Autowired(required = true)
    protected HttpServletRequest request;

//    @Autowired(required = true)
//    protected HttpServletResponse response;

    @Autowired(required = true)
    protected HttpSession session;


    public String getWebRoot(){
//        return String.format("%s://%s:%s%s", request.getScheme(),request.getServerName(),request.getServerPort(),request.getContextPath());
        //小程序要局域网才嗯那个访问
        System.out.println(HostIpUtil.getLANAddressOnWindows());
        return String.format("%s://%s:%s%s", request.getScheme(),HostIpUtil.getLANAddressOnWindows(),request.getServerPort(),request.getContextPath());
    }
}
