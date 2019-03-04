package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
    public  void saveTest(){
        ProductCategory  category = dao.findOne(1);
        category.setCategoryName("营养糕点2");
        category.setUpdateTime(new Date());
        dao.save(category);
    }


}