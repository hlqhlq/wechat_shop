package com.hlq.wxshop.service;

import com.hlq.wxshop.model.SellUser;

/**
 * @Author:HLQ
 * @Date:2019/4/10 13:54
 */
public interface SellUserService {

    SellUser save(SellUser sellUser);


    SellUser findByUsernameAndPassword(String username,String password);

    SellUser findOne(Integer id);
}
