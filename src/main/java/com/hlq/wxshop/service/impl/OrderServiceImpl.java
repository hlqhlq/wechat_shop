package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.converter.OrderMaster2OrderDTOConverter;
import com.hlq.wxshop.dao.OrderDetailDao;
import com.hlq.wxshop.dao.OrderMasterDao;
import com.hlq.wxshop.dto.CartDTO;
import com.hlq.wxshop.dto.OrderDTO;
import com.hlq.wxshop.enums.OrderStatusEnum;
import com.hlq.wxshop.enums.PayStatusEnum;
import com.hlq.wxshop.enums.ResultEnum;
import com.hlq.wxshop.exception.SellException;
import com.hlq.wxshop.model.OrderDetail;
import com.hlq.wxshop.model.OrderMaster;
import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.service.OrderService;
import com.hlq.wxshop.service.ProductInfoService;
import com.hlq.wxshop.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:HLQ
 * @Date:2019/3/11 20:18
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional(rollbackFor =Exception.class)
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId=KeyUtil.UniqueKey();
        Date date = new Date();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1.查询商品（数量，价格）
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo info = productInfoService.findOne(orderDetail.getProductId());
            if(info == null){
                 throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价  商品总价*数量  +  原来的商品总价    //multiply *      //add +
            orderAmount=info.getProductPrice().
                    multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.UniqueKey());
            orderDetail.setOrderId(orderId);
            //属性copy
            BeanUtils.copyProperties(info,orderDetail);
            orderDetail.setCreateTime(date);
            orderDetail.setUpdateTime(date);
            orderDetailDao.save(orderDetail);

        }
        //3.写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        //先copy再设置信息，不然会报null
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(date);
        orderMaster.setUpdateTime(date);
        orderMasterDao.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findone(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if(orderMaster==null){
             throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        final List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
             throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage= orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter
                .convert(orderMasterPage.getContent());
        PageImpl<OrderDTO> orderDTOPage=
                new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不准确，orderId={}，orderStatus={}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);

        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult==null){
            log.error("【取消订单】失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.addStock(cartDTOList);

        //如果已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
              //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
