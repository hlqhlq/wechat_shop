package com.hlq.wxshop.model;

import com.hlq.wxshop.enums.DelStatusEnum;
import com.hlq.wxshop.enums.OrderStatusEnum;
import com.hlq.wxshop.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表
 * @Author:HLQ
 * @Date:2019/3/11 17:05
 */
@Entity
@Data
public class OrderMaster {
    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerPostcode;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 删除状态
     */
    private Integer delStatus= DelStatusEnum.FORMAL.getCode();

    /**
     * 月份
     */
    private String month;

    /** 创建时间. */
    private String createTime;

    /** 更新时间. */
    private String updateTime;
}
