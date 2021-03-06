package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:HLQ
 * @Date:2019/3/6 11:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao dao;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("10171017");
        productInfo.setProductName("巧克力");
        productInfo.setProductPrice(new BigDecimal(10.1));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("进口的，超吃");
        productInfo.setProductImg("这是图标");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1003);
        productInfo.setCreateTime(new Date());
        productInfo.setUpdateTime(new Date());
        ProductInfo result = dao.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> product = dao.findByProductStatus(0);
        Assert.assertNotEquals(0,product.size());

    }

    @Test
    public void findByCategoryTypeIn(){
        List<ProductInfo> product = dao.findByCategoryTypeIn(1002);
        Assert.assertNotEquals(0,product.size());

    }

    @Test
    public void findGoodsByVolume(){
        List<ProductInfo> list = dao.findGoodsByVolume(4);
        System.out.println(list.get(0).getProductName());
    }

    @Test
    public void findMostHotsGoodsByHits(){
        List<ProductInfo> list = dao.findGoodsByVolume(4);
        System.out.println(list.get(0).getProductName());
    }

    @Test
    public void searchByKey(){
        //List<ProductInfo> list = dao.searchByKey("10171017", "", null);
        Pageable pageable=new PageRequest(0,1);
        Page<ProductInfo> list = dao.searchByKey("","", 1001,pageable);
        System.out.println(list.getTotalElements());
    }

}