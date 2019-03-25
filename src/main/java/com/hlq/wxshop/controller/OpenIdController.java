package com.hlq.wxshop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.config.WeChatData;
import com.hlq.wxshop.utils.MD5Util;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 发送appid和secret获取openid
 * @Author:HLQ
 * @Date:2019/3/25 13:00
 */
@RestController
@RequestMapping("/wx")
public class OpenIdController {
    @Autowired
    private WeChatData weChatData;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getOpenId")
    public ResultVO getOpenId(@RequestParam(value = "js_code") String js_code){

        String appid=weChatData.getAppid();
        String secret=weChatData.getSecret();
        String url=weChatData.getUrl();
        url=url+"?appid="+appid+"&secret="+secret+"&js_code="+js_code+"&grant_type=authorization_code";
        String str=restTemplate.getForEntity(url,String.class).getBody();
        JSONObject json= JSON.parseObject(str);
        System.out.println(json);
        System.out.println(json.getString("openid"));
        //将openid加密返回
        String md5Str=MD5Util.md5(json.getString("openid"));
        System.out.println(md5Str);
        return ResultVOUtil.success(md5Str);

    }
}
