package com.hlq.wxshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 轮播图
 * @Author:HLQ
 * @Date:2019/4/16 17:33
 */
@Entity
@Data
public class CarouselAd {
    @Id
    @GeneratedValue
    private Integer carouselId;

    private String carouselName;

    private String carouselImg;

    private Integer carouselStatus=0;

    private Date createTime;

    private Date updateTime;
}
