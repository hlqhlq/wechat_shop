package com.hlq.wxshop.converter;

import com.hlq.wxshop.dto.OrderDTO;
import com.hlq.wxshop.model.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换器
 * @Author:HLQ
 * @Date:2019/4/1 15:48
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;

    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){

        return orderMasterList.stream().map(e->
                convert(e)).collect(Collectors.toList());
    }
}
