package com.hlq.wxshop.dao;

import com.hlq.wxshop.dto.OrderDTO;
import com.hlq.wxshop.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/11 17:39
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

    /**
     * 买家openid查订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    /**
     * 买家openid 和 payStatus支付状态查询订单
     * @param buyerOpenid
     * @param payStatus
     * @return
     */
    List<OrderDTO> findByBuyerOpenidAndAndPayStatus(String buyerOpenid, Integer payStatus);

    /**
     * 根据买家openid查询订单
     * @param buyerOpenid
     * @return
     */
    List<OrderMaster> findByBuyerOpenid(String buyerOpenid);


}
