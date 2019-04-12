package com.hlq.wxshop.VO;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 返回给月份销售额订单数量封装类
 * @Author:HLQ
 * @Date:2019/4/11 18:01
 */
public class OrderVO {

    private BigDecimal totalMoney;

    private String month;

    /**
     * 订单量
     */
    private BigInteger orderNum;

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigInteger getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(BigInteger orderNum) {
        this.orderNum = orderNum;
    }

    public OrderVO() {
    }

    public OrderVO(BigDecimal totalMoney, String month, BigInteger orderNum) {
        this.totalMoney = totalMoney;
        this.month = month;
        this.orderNum = orderNum;
    }
}
