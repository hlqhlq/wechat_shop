package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:HLQ
 * @Date:2019/3/4 17:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao dao;

    @Test
    public  void findOneTest(){
        ProductCategory  category = dao.findOne(1);
        System.out.println(category.toString());
    }

    @Test
    @Transactional
    public  void saveTest(){
        ProductCategory  category = dao.findOne(1);
        category.setCategoryName("营养糕点2");
        category.setUpdateTime(new Date());
        dao.save(category);
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1001);
        List<ProductCategory> result = dao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());

    }

    @Test
    public void findByStatusAndCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1001,1002);
        List<ProductCategory> result = dao.findByCategoryStatusAndCategoryTypeIn(0,list);
        System.out.println(result.size());
//        Assert.assertNotEquals(0,result.size());

    }

    @Test
    public void findByCategoryStatus(){
        List<ProductCategory> list = dao.findByCategoryStatus(0);
        System.out.println(list.size());

    }

}