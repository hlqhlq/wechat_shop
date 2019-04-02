package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:HLQ
 * @Date:2019/3/11 18:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private  OrderDetailDao dao;

    @Test
    public void save() {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567810");
        orderDetail.setOrderId("1234567");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("10171017");
        orderDetail.setProductName("巧克力");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(3);
//        orderDetail.setCreateTime(new Date());
//        orderDetail.setUpdateTime(new Date());

        OrderDetail result = dao.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> result = dao.findByOrderId("1234567");
        Assert.assertNotEquals(0,result.size());

    }
}