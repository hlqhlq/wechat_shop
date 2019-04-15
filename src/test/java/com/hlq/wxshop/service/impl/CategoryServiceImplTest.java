package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:HLQ
 * @Date:2019/3/6 10:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl service;

    @Test
    public void findOne() {
        ProductCategory result = service.findOne(1);
        Assert.assertEquals(new Integer(1),result.getCategoryType());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = service.findAll();
        Assert.assertNotEquals(0,list.size());

    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory>  list = service.findByCategoryTypeIn(Arrays.asList(1001, 1002));
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void save() {
         ProductCategory category=new ProductCategory("进口零食","图标",0,new Date(),new Date());
         ProductCategory result = service.save(category);
         Assert.assertNotNull(result);

    }
}