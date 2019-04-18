package com.hlq.wxshop.controller.pc;

import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.model.SellUser;
import com.hlq.wxshop.service.SellUserService;
import com.hlq.wxshop.utils.DateFormatUtil;
import com.hlq.wxshop.utils.MD5Util;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author:HLQ
 * @Date:2019/4/18 15:31
 */
@RestController
@RequestMapping("/pc/user")
public class PCUserController {

    @Autowired
    private SellUserService sellUserService;

    @PostMapping("/update")
    public ResultVO update(SellUser sellUser){
        SellUser one = sellUserService.findOne(sellUser.getId());
        String md5pwd= MD5Util.md5(sellUser.getPassword());
        one.setPassword(md5pwd);
        one.setUsername(sellUser.getUsername());
        one.setUpdateTime(DateFormatUtil.getCurrentTimeBySecond(new Date()));
        SellUser save = sellUserService.save(one);
        return ResultVOUtil.success(save);
    }
}
