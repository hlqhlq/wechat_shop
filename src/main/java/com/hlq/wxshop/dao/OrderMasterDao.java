package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
