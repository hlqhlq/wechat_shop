package com.hlq.wxshop.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.model.CarouselAd;
import com.hlq.wxshop.service.CarouselService;
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
 * @Date:2019/4/17 12:26
 */
@RestController
@RequestMapping("/pc/carousel")
public class PCCarouselController {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/findAllBySplitePage")
    public JSONObject findAllBySplitePage(String categoryName, Integer page, Integer limit){
        Pageable request = new PageRequest(page - 1, limit);
        Page<CarouselAd> info = carouselService.findAllBySplitePage(categoryName, request);
        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", info.getTotalElements());
        obj.put("data", info.getContent());
        return obj;
    }

    @GetMapping("/putOn")
    public ResultVO putOn(Integer carouselId){
        CarouselAd carouselAd = carouselService.putOn(carouselId);
        return ResultVOUtil.success(carouselAd);
    }

    @GetMapping("/takeOff")
    public ResultVO takeOff(Integer carouselId){
        CarouselAd carouselAd = carouselService.takeOff(carouselId);
        return ResultVOUtil.success(carouselAd);
    }

    @PostMapping("/update")
    public ResultVO update(CarouselAd carouselAd){
        CarouselAd one = carouselService.findOne(carouselAd.getCarouselId());
        one.setCarouselName(carouselAd.getCarouselName());
        one.setCarouselImg(carouselAd.getCarouselImg());
        one.setUpdateTime(new Date());
        CarouselAd save = carouselService.save(one);
        return ResultVOUtil.success(save);
    }

    @GetMapping("/searchByCarouselName")
    public JSONObject searchByCarouselName(String carouselName,Integer page,Integer limit){
        Pageable request=new PageRequest(page-1,limit);
        Page<CarouselAd> info = carouselService.searchByCarouselName(carouselName,request);
        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",info.getTotalElements());
        obj.put("data", info.getContent());
        return  obj;
    }

    @GetMapping("/deleteById")
    public ResultVO deleteById(Integer carouselId){
        carouselService.deleteById(carouselId);
        return ResultVOUtil.success();
    }

    @PostMapping("/deleteBatch")
    public ResultVO deleteById(String batchId){
        String[] str = batchId.split(",");
        for(String s:str){
            carouselService.deleteById(Integer.parseInt(s));
        }
        return ResultVOUtil.success();
    }

    @PostMapping("/save")
    public ResultVO save(CarouselAd carouselAd){
        Date date=new Date();
        carouselAd.setCreateTime(date);
        carouselAd.setUpdateTime(date);
        CarouselAd save = carouselService.save(carouselAd);
        return ResultVOUtil.success(save);
    }
}
