package com.hlq.wxshop.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 返回给微信前端公告走马灯需要的数据
 * @Author:HLQ
 * @Date:2019/4/17 19:44
 */
@Data
public class NoticeVO {


    @JsonProperty("notice")
    private String noticeContent;
    /**
     * 间距  wx.getSystemInfoSync().windowWidth; //以取屏幕宽度为间距
     */
    @JsonProperty("starspos")
    private Integer starspos=0;
}
