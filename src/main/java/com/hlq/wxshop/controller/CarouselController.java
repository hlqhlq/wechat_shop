package com.hlq.wxshop.controller;

import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.enums.CarouselStatusEnum;
import com.hlq.wxshop.model.CarouselAd;
import com.hlq.wxshop.service.CarouselService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/16 18:06
 */
@RestController
@RequestMapping("/wx/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    /**
     *查找开启状态的轮播图
     * @return
     */
    @GetMapping("/findByCarouselStatusOn")
    public ResultVO findByCarouselStatus(){
        List<CarouselAd> list = carouselService.findByCarouselStatus(CarouselStatusEnum.ON.getCode());
        return ResultVOUtil.success(list);
    }

}
