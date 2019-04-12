package com.hlq.wxshop.dao;

import com.hlq.wxshop.VO.OrderVO;
import com.hlq.wxshop.model.OrderMaster;
import com.hlq.wxshop.utils.CastEntityUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:HLQ
 * @Date:2019/3/11 17:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao dao;

    private final String OPENID = "110110";

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("123456789123");
        orderMaster.setBuyerAddress("珠海");
        orderMaster.setBuyerPostcode("515100");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));
//        orderMaster.setCreateTime(new Date());
//        orderMaster.setUpdateTime(new Date());

        OrderMaster result = dao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid(){
        PageRequest request=new PageRequest(0,1);
        Page<OrderMaster> result = dao.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0,result.getTotalElements());
        System.out.println(result.getTotalElements());

    }

    @Test
    public void countAll(){
        Integer count = dao.countAllBy();
        System.out.println(count);
    }

    @Test
    public void findTotalMoneyByMonth(){
        List<Object[]> result = dao.findTotalMoneyByMonth(3);
//        List<OrderVO> list=new ArrayList<>();
//        for(int i=0;i<result.size();i++){
//            OrderVO orderVO=new OrderVO();
//            Object[] obj = (Object[])result.get(i);
//            orderVO.setTotalMoney((BigDecimal) obj[0]);
//            orderVO.setMonth(obj[1].toString());
//            orderVO.setOrderNum((BigInteger) obj[2]);
//            list.add(orderVO);
//        }
        OrderVO orderVO = new OrderVO();
        List<OrderVO> orderVOS = CastEntityUtil.castEntity(result, OrderVO.class, orderVO);
        for(int i=0;i<orderVOS.size();i++){
            System.out.println(orderVOS.get(i).getOrderNum());
        }
    }
}