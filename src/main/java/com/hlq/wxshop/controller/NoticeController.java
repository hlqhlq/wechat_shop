package com.hlq.wxshop.controller;

import com.hlq.wxshop.VO.NoticeVO;
import com.hlq.wxshop.VO.ProductVO;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.enums.NoticeStatusEnum;
import com.hlq.wxshop.model.ShopNotice;
import com.hlq.wxshop.service.NoticeService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/17 20:04
 */
@RestController
@RequestMapping("/wx/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 查询开启状态的公告
     * @return
     */
    @GetMapping("/findByStatusOn")
    public ResultVO findByStatusOn(){
        List<ShopNotice> list = noticeService.findByNoticeStatus(NoticeStatusEnum.ON.getCode());
        List<NoticeVO> VOList = new ArrayList<>();
        for(ShopNotice notice:list){
            NoticeVO noticeVO = new NoticeVO();
            noticeVO.setNoticeContent(notice.getNoticeContent());
            noticeVO.setStarspos(0);
            VOList.add(noticeVO);
        }
        return ResultVOUtil.success(VOList);

    }
}
