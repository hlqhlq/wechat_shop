package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.model.UserAddress;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author:HLQ
 * @Date:2019/3/25 19:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressDaoTest {

    @Autowired
    private AddressDao dao;

    @Test
    public void saveTest(){
        UserAddress address=new UserAddress();
        address.setAddressUsername("小何");
        address.setAddressTelnumber("111111111");
        address.setAddressPostalcode("510000");
        address.setAddressProvincename("广东省");
        address.setAddressCityname("广州市");
        address.setAddressCountryname("越秀区");
        address.setAddressDetailinfo("水荫路");
        address.setOpenid("klsadjl5445sjdk");
        UserAddress result = dao.save(address);
        Assert.assertNotNull(result);
    }

}