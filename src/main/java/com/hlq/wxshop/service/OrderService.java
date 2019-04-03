package com.hlq.wxshop.service;

import com.hlq.wxshop.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/11 20:03
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
   OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
   OrderDTO findOne(String  orderId);

    /**
     * 根据buyerOpenid和order、支付状态查询订单
     * @param
     * @return
     */
   List<OrderDTO> findByBuyerOpenidAndAndOrderStatusAndAndPayStatus(String buyerOpenid,Integer orderStatus,Integer payStatus);
    /**
     * 查询订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
   Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
   OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     * @param orderDTO
     * @return
     */
   OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
   OrderDTO paid(OrderDTO orderDTO);
}
