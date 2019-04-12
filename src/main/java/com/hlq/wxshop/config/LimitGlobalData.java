package com.hlq.wxshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * admin首页 月份 商品浏览量以及 商品销量排行  （limit 的值）
 * @Author:HLQ
 * @Date:2019/4/11 23:33
 */
@Component
@ConfigurationProperties(prefix = "limit")
public class LimitGlobalData {

    private Integer monthNum;
    private Integer productNum;
    private Integer hitsNum;

    public Integer getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(Integer monthNum) {
        this.monthNum = monthNum;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getHitsNum() {
        return hitsNum;
    }

    public void setHitsNum(Integer hitsNum) {
        this.hitsNum = hitsNum;
    }
}

