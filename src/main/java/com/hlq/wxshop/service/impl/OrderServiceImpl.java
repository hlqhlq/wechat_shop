package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.dao.OrderDetailDao;
import com.hlq.wxshop.dao.OrderMasterDao;
import com.hlq.wxshop.dto.CartDTO;
import com.hlq.wxshop.dto.OrderDTO;
import com.hlq.wxshop.enums.ResultEnum;
import com.hlq.wxshop.exception.SellException;
import com.hlq.wxshop.model.OrderDetail;
import com.hlq.wxshop.model.OrderMaster;
import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.service.OrderService;
import com.hlq.wxshop.service.ProductInfoService;
import com.hlq.wxshop.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:HLQ
 * @Date:2019/3/11 20:18
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
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
            orderAmount=orderDetail.getProductPrice().
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
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO,orderMaster);
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
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
