package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.dao.AddressDao;
import com.hlq.wxshop.enums.AddressStatusEnum;
import com.hlq.wxshop.model.UserAddress;
import com.hlq.wxshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/25 18:53
 */
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressDao addressDao;

    @Override
    public UserAddress save(UserAddress address) {
        return addressDao.save(address);
    }

    @Override
    public List<UserAddress> findByOpenidIn(String openid) {
        return addressDao.findByOpenidIn(openid);
    }

    @Override
    public void deleteById(Integer id) {
        addressDao.delete(id);
    }

    @Override
    public UserAddress findById(Integer id) {
        return addressDao.findOne(id);
    }

    @Override
    public UserAddress findByAddressDefaultEqualsAndOpenid(Integer code, String openid) {
        return addressDao.findByAddressDefaultEqualsAndOpenid(code,openid);
    }




}
