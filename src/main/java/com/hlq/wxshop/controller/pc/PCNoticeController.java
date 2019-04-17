package com.hlq.wxshop.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.model.CarouselAd;
import com.hlq.wxshop.model.ShopNotice;
import com.hlq.wxshop.service.NoticeService;
import com.hlq.wxshop.utils.DateFormatUtil;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author:HLQ
 * @Date:2019/4/18 0:21
 */
@RestController
@RequestMapping("/pc/notice")
public class PCNoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/findAllBySplitePage")
    public JSONObject findAllBySplitePage(String noticeContent,Integer page,Integer limit){
        Pageable request = new PageRequest(page - 1, limit);
        Page<ShopNotice> info = noticeService.findAllBySplitePage(noticeContent,request);
        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", info.getTotalElements());
        obj.put("data", info.getContent());
        return obj;
    }

    @PostMapping("/update")
    public ResultVO update(ShopNotice shopNotice){
        ShopNotice one = noticeService.findOne(shopNotice.getNoticeId());
        one.setNoticeContent(shopNotice.getNoticeContent());
        one.setUpdateTime(DateFormatUtil.getCurrentTimeBySecond(new Date()));
        ShopNotice save = noticeService.save(one);
        return ResultVOUtil.success(save);
    }

    @GetMapping("/putOn")
    public ResultVO putOn(Integer noticeId){
        ShopNotice shopNotice = noticeService.putOn(noticeId);
        return ResultVOUtil.success(shopNotice);
    }

    @GetMapping("/takeOff")
    public ResultVO takeOff(Integer noticeId){
        ShopNotice shopNotice = noticeService.takeOff(noticeId);
        return ResultVOUtil.success(shopNotice);
    }
}
