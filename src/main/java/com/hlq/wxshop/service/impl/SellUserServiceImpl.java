package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.dao.SellUserDao;
import com.hlq.wxshop.model.SellUser;
import com.hlq.wxshop.service.SellUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:HLQ
 * @Date:2019/4/10 14:00
 */
@Service
public class SellUserServiceImpl implements SellUserService{

    @Autowired
    private SellUserDao sellUserDao;

    @Override
    @Transactional(rollbackFor =Exception.class)
    public SellUser save(SellUser sellUser) {
        return sellUserDao.save(sellUser);
    }

    @Override
    public SellUser findByUsernameAndPassword(String username, String password) {
        return sellUserDao.findByUsernameAndPassword(username,password);
    }


    @Override
    public SellUser findOne(Integer id) {
        return sellUserDao.findOne(id);
    }
}
