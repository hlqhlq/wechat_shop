package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/25 18:43
 */
public interface AddressDao extends JpaRepository<UserAddress,Integer> {

    List<UserAddress> findByOpenidIn(String openid);
}
