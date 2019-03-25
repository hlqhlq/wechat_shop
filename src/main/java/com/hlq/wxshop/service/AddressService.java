package com.hlq.wxshop.service;

import com.hlq.wxshop.model.UserAddress;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/25 18:47
 */
public interface AddressService {

    /**
     * 增加或修改地址
     * @param address
     * @return
     */
    UserAddress save (UserAddress address);

    /**
     * 根据openid查询地址列表
     * @param openid
     * @return
     */
    List<UserAddress> findByOpenidIn(String openid);

    /**
     * 根据主键id删除地址
     */
    void deleteById(Integer id);

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    UserAddress findById(Integer id);

}
