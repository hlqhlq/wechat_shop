package com.hlq.wxshop.VO;

/**
 * 封装order搜索条件的实体
 * @Author:HLQ
 * @Date:2019/4/14 20:05
 */
public class OrderSearchVO {

    private String orderIdKey;
    private String startDate;
    private String endDate;
    private Integer orderStatusKey;
    private Integer payStatusKey;

    public OrderSearchVO() {
    }

    public String getOrderIdKey() {
        return orderIdKey;
    }

    public void setOrderIdKey(String orderIdKey) {
        this.orderIdKey = orderIdKey;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getOrderStatusKey() {
        return orderStatusKey;
    }

    public void setOrderStatusKey(Integer orderStatusKey) {
        this.orderStatusKey = orderStatusKey;
    }

    public Integer getPayStatusKey() {
        return payStatusKey;
    }

    public void setPayStatusKey(Integer payStatusKey) {
        this.payStatusKey = payStatusKey;
    }
}
