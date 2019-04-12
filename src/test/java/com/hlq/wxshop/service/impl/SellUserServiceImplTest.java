package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.model.SellUser;
import com.hlq.wxshop.utils.DateFormatUtil;
import com.hlq.wxshop.utils.MD5Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author:HLQ
 * @Date:2019/4/10 14:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellUserServiceImplTest {

    @Autowired
    private SellUserServiceImpl sellUserService;

    @Test
    public void save() {
        SellUser sellUser = new SellUser();
        sellUser.setUsername("hlq");
        String pwd = MD5Util.md5("1017");
        sellUser.setPassword(pwd);
        sellUser.setIsDel(0);
        Date date = new Date();
        sellUser.setCreateTime(DateFormatUtil.getCurrentTimeBySecond(date));
        sellUser.setUpdateTime(DateFormatUtil.getCurrentTimeBySecond(date));
        SellUser result = sellUserService.save(sellUser);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByUsernameAndPassword() {
        String pwd = MD5Util.md5("1017");
        SellUser result = sellUserService.findByUsernameAndPassword("hlq", pwd);
        Assert.assertNotNull(result);
    }
}