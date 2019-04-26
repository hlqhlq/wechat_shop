package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.CarouselAd;
import com.hlq.wxshop.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/16 17:42
 */
public interface CarouselDao extends JpaRepository<CarouselAd,Integer> {


    /**
     * 分页模糊查询
     * @param carouselName
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,value = "select * from carousel_ad where (carousel_name like CONCAT('%',?1,'%') or ?1 is null)order by create_time desc,?#{#pageable}")
    Page<CarouselAd> findAllBySplitePage(String carouselName,Pageable pageable);

    /**
     * 根据轮播图状态查询
     * @param code
     * @return
     */
    List<CarouselAd> findByCarouselStatus(Integer code);


    /**
     * 模糊查询
     * @param carouselName
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,value = "select * from  carousel_ad where ( carousel_name like CONCAT('%',?1,'%') or ?1 is null)order by create_time desc,?#{#pageable}")
    Page<CarouselAd> searchByCarouselName(String carouselName, Pageable pageable);
}
