package com.hlq.wxshop.service;

import com.hlq.wxshop.model.ShopNotice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/17 19:38
 */
public interface NoticeService {

    /**
     * 分页以及模糊查询
     * @param noticeContent
     * @param pageable
     * @return
     */
    Page<ShopNotice> findAllBySplitePage(String noticeContent, Pageable pageable);


    /**
     * 根据状态查询
     * @param code
     * @return
     */
    List<ShopNotice> findByNoticeStatus(Integer code);

    /**
     * 添加公告
     * @param shopNotice
     * @return
     */
    ShopNotice save(ShopNotice shopNotice);

    /**
     * 开启轮播图
     * @param noticeId
     * @return
     */
    ShopNotice putOn(Integer noticeId);

    /**
     * 禁用轮播图
     * @param noticeId
     * @return
     */
    ShopNotice takeOff(Integer noticeId);

    /**
     * 删除
     * @param noticeId
     */
    void deleteById(Integer noticeId);

    /**
     * id查找
     * @param noticeId
     * @return
     */
    ShopNotice findOne(Integer noticeId);
}
