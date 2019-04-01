package com.hlq.wxshop.controller;

import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.dto.OrderDTO;
import com.hlq.wxshop.service.OrderService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:HLQ
 * @Date:2019/4/1 17:26
 */
@RestController
@RequestMapping("/wx/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    //@requestBody User user 这种形式会将JSON字符串中的值赋予user中对应的属性上
    //需要注意的是，JSON字符串中的key必须对应user中的属性名，否则是请求不过去的。
    public ResultVO createOrder(@RequestBody OrderDTO orderDTO){

        OrderDTO result = orderService.create(orderDTO);
        return ResultVOUtil.success(result);
    }
}
