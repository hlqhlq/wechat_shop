package com.hlq.wxshop.dao;

import com.hlq.wxshop.model.CarouselAd;
import com.hlq.wxshop.model.ShopNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/17 19:29
 */
public interface NoticeDao extends JpaRepository<ShopNotice,Integer> {


    /**
     * 分页模糊查询
     * @param noticeContent
     * @param pageable
     * @return
     */
    @Query(nativeQuery = true,value = "select * from shop_notice where (notice_content like CONCAT('%',?1,'%') or ?1 is null)order by ?#{#pageable}")
    Page<ShopNotice> findAllBySplitePage(String noticeContent, Pageable pageable);

    /**
     * 根据状态查询
     * @param code
     * @return
     */
    List<ShopNotice> findByNoticeStatus(Integer code);

}
