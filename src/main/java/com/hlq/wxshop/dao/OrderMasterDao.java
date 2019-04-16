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
    @Query(nativeQuery =true,value="select sum(order_amount) as totalMoney,month,count(order_id) as orderNum from order_master where order_status=3  group by month order by month asc limit ?1")
    List<Object[]> findTotalMoneyByMonth(Integer limit);

    /**
     * 根据搜索条件查询order  分页
     * @param orderIdKey
     * @param startDate
     * @param endDate
     * @param orderStatusKey
     * @param payStatusKey
     * @return
     */
//    @Query(nativeQuery = true,value = "select * from order_master om where(om.order_id like CONCAT('%',?1,'%') or ?1 is null) and (om.create_time >= ?2 or ?2 is null) and (om.update_time <= ?3 or ?3 is null) and (om.order_status= ?4 or ?4 is null) and (om.pay_status= ?5 or ?5 is null)order by ?#{#pageable}")
//    Page<OrderMaster> searchByKey(String orderId,String createTime,String updateTime,Integer orderStatus,Integer payStatus,Pageable pageable);


    //@Query(nativeQuery = true,value = "select * from order_master om where (om.order_id like CONCAT('%',?1,'%')or ?1 is null) and (om.order_status=?2 or ?2 is null) and (om.pay_status=?3 or ?3 is null) and (om.create_time>=?4 or ?4 is null) and (om.create_time<=?5 or ?5 is null) order by ?#{#pageable}")
    @Query(nativeQuery = true,value = "select * from order_master om where (om.order_id like CONCAT('%',?1,'%')or ?1 is null) and (om.order_status=?2 or ?2 is null) and (om.pay_status=?3 or ?3 is null) and if(?4!='',om.create_time>=?4,1=1) and if(?5!='',om.create_time<=?5,1=1) order by om.create_time desc,?#{#pageable}")
    Page<OrderMaster> searchByKey(String orderId,Integer orderStatus,Integer payStatus,String startDate,String endDate,Pageable pageable);
}
