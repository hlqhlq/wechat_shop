package com.hlq.wxshop.dto;


import com.hlq.wxshop.model.OrderDetail;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO data transfer object
 * 数据传输对象，这个对象封装你需要传输的数据 在M，V，C这三个层传递
 * @Author:HLQ
 * @Date:2019/3/11 20:07
 */
@Data
public class OrderDTO {

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
    private Integer orderStatus ;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus ;

    /** 创建时间. */
    private String createTime;

    /** 更新时间. */
    private String updateTime;

    List<OrderDetail> orderDetailList;
}
