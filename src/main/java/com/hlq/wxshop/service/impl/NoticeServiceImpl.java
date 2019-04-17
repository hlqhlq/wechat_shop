package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.dao.NoticeDao;
import com.hlq.wxshop.enums.NoticeStatusEnum;
import com.hlq.wxshop.model.ShopNotice;
import com.hlq.wxshop.service.NoticeService;
import com.hlq.wxshop.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/17 19:56
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao dao;

    @Override
    public Page<ShopNotice> findAllBySplitePage(String noticeContent, Pageable pageable) {
        return dao.findAllBySplitePage(noticeContent,pageable);
    }

    @Override
    public List<ShopNotice> findByNoticeStatus(Integer code) {
        return dao.findByNoticeStatus(code);
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public ShopNotice save(ShopNotice shopNotice) {
        return dao.save(shopNotice);
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public ShopNotice putOn(Integer noticeId) {
        ShopNotice one = dao.findOne(noticeId);
        one.setNoticeStatus(NoticeStatusEnum.ON.getCode());
        one.setUpdateTime(DateFormatUtil.getCurrentTimeBySecond(new Date()));
        ShopNotice save = dao.save(one);
        return save;
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public ShopNotice takeOff(Integer noticeId) {
        ShopNotice one = dao.findOne(noticeId);
        one.setNoticeStatus(NoticeStatusEnum.OFF.getCode());
        one.setUpdateTime(DateFormatUtil.getCurrentTimeBySecond(new Date()));
        ShopNotice save = dao.save(one);
        return save;
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public void deleteById(Integer noticeId) {
        dao.delete(noticeId);
    }

    @Override
    public ShopNotice findOne(Integer noticeId) {
        return dao.findOne(noticeId);
    }
}
