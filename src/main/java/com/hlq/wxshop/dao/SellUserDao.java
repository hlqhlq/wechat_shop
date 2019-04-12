package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.SellUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:HLQ
 * @Date:2019/4/10 13:44
 */
public interface SellUserDao extends JpaRepository<SellUser,Integer> {

    SellUser findByUsernameAndPassword(String username,String password);

    SellUser findByUsername(String username);
}
