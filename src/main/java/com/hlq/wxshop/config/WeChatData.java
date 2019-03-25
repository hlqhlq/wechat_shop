package com.hlq.wxshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 定义小程序的appid和secret bean
 * @Author:HLQ
 * @Date:2019/3/25 12:44
 */
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatData {
    private String Appid;
    private String Secret;
    private String Url;

    public String getAppid() {
        return Appid;
    }

    public void setAppid(String appid) {
        Appid = appid;
    }

    public String getSecret() {
        return Secret;
    }

    public void setSecret(String secret) {
        Secret = secret;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
