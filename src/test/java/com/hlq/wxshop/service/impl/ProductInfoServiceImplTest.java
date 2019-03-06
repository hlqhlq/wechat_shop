package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.enums.ProductStatusEnum;
import com.hlq.wxshop.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:HLQ
 * @Date:2019/3/6 14:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl service;
    @Test
    public void findOne() {
        ProductInfo info = service.findOne("10171017");
        Assert.assertEquals("10171017",info.getProductId());

    }

    @Test
    public void findOnAll() {
        List<ProductInfo> list = service.findOnAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> page = service.findAll(pageRequest);
        System.out.println(page.getTotalElements());
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("10171018");
        productInfo.setProductName("卫龙");
        productInfo.setProductPrice(new BigDecimal(10.1));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("进口的，超吃");
        productInfo.setProductImg("这是图标");
        productInfo.setProductStatus(ProductStatusEnum.Down.getCode());
        productInfo.setCategoryType(1003);
        productInfo.setCreateTime(new Date());
        productInfo.setUpdateTime(new Date());
        ProductInfo info = service.save(productInfo);
        Assert.assertNotNull(info);
    }
}