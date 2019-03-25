package com.hlq.wxshop.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author:HLQ
 * @Date:2019/3/25 12:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatDataTest {

    @Autowired
    private WeChatData weChatData;
    @Test
    public void getAppid() {
        System.out.println(weChatData.getAppid());
    }

    @Test
    public void getSecret() {
        System.out.println(weChatData.getSecret());
    }

    @Test
    public void getUrl() {
        System.out.println(weChatData.getUrl());
    }
}