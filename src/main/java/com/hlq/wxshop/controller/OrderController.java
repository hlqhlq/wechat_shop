package com.hlq.wxshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlq.wxshop.VO.OrderVO;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.dto.OrderDTO;
import com.hlq.wxshop.enums.DelStatusEnum;
import com.hlq.wxshop.enums.OrderStatusEnum;
import com.hlq.wxshop.enums.PayStatusEnum;
import com.hlq.wxshop.model.OrderMaster;
import com.hlq.wxshop.service.OrderService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/1 17:26
 */
@RestController
@RequestMapping("/wx/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 生成订单
     * @param orderDTO
     * @return
     */
    @PostMapping("/create")
    //@requestBody User user 这种形式会将JSON字符串中的值赋予user中对应的属性上
    //需要注意的是，JSON字符串中的key必须对应user中的属性名，否则是请求不过去的。
    public ResultVO createOrder(@RequestBody OrderDTO orderDTO){

        OrderDTO result = orderService.create(orderDTO);
        return ResultVOUtil.success(result);
    }

    /**
     * 根基订单状态、支付状态查询订单
     * @param openid
     * @param orderStatus
     * @param payStatus
     * @return
     */
    @GetMapping("/findByPayStatus")
    public ResultVO findByPayStatus(String openid,Integer orderStatus,Integer payStatus,Integer delStatus){
        List<OrderDTO> list = orderService
                .findByBuyerOpenidAndAndOrderStatusAndAndPayStatus(openid,orderStatus,payStatus,delStatus);
        return ResultVOUtil.success(list);
    }

    /**
     * 取消订单
     * @param openId
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ResultVO cancelOrder(@RequestParam(value = "openId") String openId,
                                @RequestParam(value = "orderId")String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO.getBuyerOpenid().equals(openId)){
            orderService.cancel(orderDTO);
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(1,"用户openid出错");
    }

    /**
     * 根据openid查询订单
     * @param orderId
     * @return
     */
    @GetMapping("/findOne")
    public ResultVO findOne(String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        return ResultVOUtil.success(orderDTO);

    }


    /**
     * 查询各个状态的订单数量
     * @return
     */
    @GetMapping("/countOrder")
    public ResultVO countOrder(){
        Integer daifukuan = orderService.countByOrderStatusAndPayStatus(OrderStatusEnum.NEW.getCode(), PayStatusEnum.WAIT.getCode());
        Integer daifahuo= orderService.countByOrderStatusAndPayStatus(OrderStatusEnum.NEW.getCode(), PayStatusEnum.SUCCESS.getCode());
        Integer daishouhuo= orderService.countByOrderStatusAndPayStatus(OrderStatusEnum.DELIVERY.getCode(), PayStatusEnum.SUCCESS.getCode());
        Integer daipingjia=orderService.countByOrderStatusAndPayStatusAndAndDelStatus(OrderStatusEnum.RECEIVED.getCode(),
                PayStatusEnum.SUCCESS.getCode(),DelStatusEnum.FORMAL.getCode());
        Integer finish= orderService.countByOrderStatusAndPayStatusAndAndDelStatus(OrderStatusEnum.FINISHED.getCode(),
                PayStatusEnum.SUCCESS.getCode(), DelStatusEnum.FORMAL.getCode());
        JSONObject obj=new JSONObject();
        obj.put("daifukuan",daifukuan);
        obj.put("daifahuo",daifahuo);
        obj.put("daishouhuo",daishouhuo);
        obj.put("daipingjia",daipingjia);
        obj.put("finish",finish);
        return ResultVOUtil.success(obj);

    }

    @GetMapping("/delete")
    public ResultVO delete(String orderId){
        OrderMaster orderMaster = orderService.delete(orderId);
        return ResultVOUtil.success(orderMaster);
    }

}
