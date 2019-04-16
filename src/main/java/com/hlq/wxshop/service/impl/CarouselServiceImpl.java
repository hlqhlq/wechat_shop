package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.dao.CarouselDao;
import com.hlq.wxshop.enums.CarouselStatusEnum;
import com.hlq.wxshop.model.CarouselAd;
import com.hlq.wxshop.model.ProductCategory;
import com.hlq.wxshop.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/16 17:57
 */
@Service
public class CarouselServiceImpl implements CarouselService {


    @Autowired
    private CarouselDao dao;

    @Override
    public Page<CarouselAd> findAllBySplitePage(String carouselName, Pageable pageable) {
        Page<CarouselAd> page = dao.findAllBySplitePage(carouselName, pageable);
        return page;
    }

    @Override
    public List<CarouselAd> findByCarouselStatus(Integer code) {
        return dao.findByCarouselStatus(code);
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public CarouselAd save(CarouselAd carouselAd) {
        return dao.save(carouselAd);
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public CarouselAd putOn(Integer carouselId) {
        CarouselAd one = dao.findOne(carouselId);
        one.setCarouselStatus(CarouselStatusEnum.ON.getCode());
        one.setUpdateTime(new Date());
        return dao.save(one);
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public CarouselAd takeOff(Integer carouselId) {
        CarouselAd one = dao.findOne(carouselId);
        one.setCarouselStatus(CarouselStatusEnum.OFF.getCode());
        one.setUpdateTime(new Date());
        return dao.save(one);
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public void deleteById(Integer carouselId) {
         dao.delete(carouselId);
    }

    @Override
    public CarouselAd findOne(Integer carouselId) {
        return dao.findOne(carouselId);
    }

    @Override
    public Page<CarouselAd> searchByCarouselName(String carouselName, Pageable pageable) {
        Page<CarouselAd> page = dao.searchByCarouselName(carouselName, pageable);
        return page;
    }
}
