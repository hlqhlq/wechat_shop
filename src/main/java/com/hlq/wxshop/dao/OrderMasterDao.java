package com.hlq.wxshop.dao;

import com.hlq.wxshop.VO.OrderVO;
import com.hlq.wxshop.config.LimitGlobalData;
import com.hlq.wxshop.dto.OrderDTO;
import com.hlq.wxshop.model.OrderMaster;
import com.hlq.wxshop.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
     * 买家openid 和 orderStatus和payStatus支付状态查询订单
     * @param buyerOpenid
     * @param payStatus
     * @return
     */
    List<OrderMaster> findByBuyerOpenidAndAndOrderStatusAndAndPayStatus(String buyerOpenid, Integer orderStatus,Integer payStatus);

    /**
     * 根据买家openid查询订单
     * @param buyerOpenid
     * @return
     */
    List<OrderMaster> findByBuyerOpenid(String buyerOpenid);


    /**
     * 查询全部订单数量
     * @return
     */
    Integer countAllBy();

    /**
     * 查询所有待发货订单数量
     * @param order_status
     * @param pay_status
     * @return
     */
    Integer countByOrderStatusAndPayStatus(Integer order_status,Integer pay_status);


    /**
     * 只展示3个月的
     * 根据订单月份分组返回订单数量以及总销售额
     * @return
     */
    @Query(nativeQuery =true,value="select sum(order_amount) as totalMoney,month,count(order_id) as orderNum from order_master where pay_status=1  group by month order by month asc limit ?1")
    List<Object[]> findTotalMoneyByMonth(Integer limit);


}
