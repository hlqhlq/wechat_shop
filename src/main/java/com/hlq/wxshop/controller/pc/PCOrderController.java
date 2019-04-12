package com.hlq.wxshop.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.hlq.wxshop.VO.OrderVO;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.config.LimitGlobalData;
import com.hlq.wxshop.enums.OrderStatusEnum;
import com.hlq.wxshop.enums.PayStatusEnum;
import com.hlq.wxshop.service.OrderService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:HLQ
 * @Date:2019/4/11 15:22
 */
@RestController
@RequestMapping("/pc/order")
public class PCOrderController {

    @Autowired
    private LimitGlobalData limitGlobalData;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderCount")
    public ResultVO count(){
        Integer countAll = orderService.countOrder();
        Integer daifahuo = orderService.countDaifahuo(OrderStatusEnum.NEW.getCode(), PayStatusEnum.SUCCESS.getCode());
        HashMap<String, Integer> map = new HashMap<>();
        map.put("countAll",countAll);
        map.put("daifahuo",daifahuo);
        return ResultVOUtil.success(map);
    }

    /**
     * 返回给echart图表的数据
     * @return
     */
    @GetMapping("/findTotalMoneyByMonth")
    public ResultVO findTotalMoneyByMonth(){
        List<OrderVO> list = orderService.findTotalMoneyByMonth();
        String[] month=new String[limitGlobalData.getMonthNum()];
        BigDecimal[] totalMoney=new BigDecimal[limitGlobalData.getMonthNum()];
        BigInteger[] orderNum=new BigInteger[limitGlobalData.getMonthNum()];
        for(int i=0;i<list.size();i++){
            month[i]=list.get(i).getMonth();
            totalMoney[i]=list.get(i).getTotalMoney();
            orderNum[i]=list.get(i).getOrderNum();
        }
        JSONObject obj=new JSONObject();
        obj.put("month",month);
        obj.put("totalMoney",totalMoney);
        obj.put("orderNum",orderNum);
        return  ResultVOUtil.success(obj);
    }
}
