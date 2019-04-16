package com.hlq.wxshop.service;

import com.hlq.wxshop.model.CarouselAd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/16 17:41
 */
public interface CarouselService {
    /**
     * 分页模糊查询
      * @param carouselName
     * @param pageable
     * @return
     */
    Page<CarouselAd> findAllBySplitePage(String carouselName, Pageable pageable);

    /**
     * 轮播图状态查询
     * @param code
     * @return
     */
    List<CarouselAd> findByCarouselStatus(Integer code);

    /**
     * 增加轮播图
     * @param carouselAd
     * @return
     */
    CarouselAd save(CarouselAd carouselAd);

    /**
     * 开启轮播图
     * @param carouselId
     * @return
     */
    CarouselAd putOn(Integer carouselId);

    /**
     * 禁用轮播图
     * @param carouselId
     * @return
     */
    CarouselAd takeOff(Integer carouselId);


    /**
     * 删除
     * @param carouselId
     */
    void deleteById(Integer carouselId);

    /**
     * id查找
     * @param carouselId
     * @return
     */
    CarouselAd findOne(Integer carouselId);


    /**
     * 模糊查询
     * @param carouselName
     * @param pageable
     * @return
     */
    Page<CarouselAd> searchByCarouselName(String carouselName,Pageable pageable);

}
