package com.hlq.wxshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author:HLQ
 * @Date:2019/4/17 19:25
 */
@Entity
@Data
public class ShopNotice {
    @Id
    @GeneratedValue
    private Integer noticeId;

    private String noticeContent;

    private Integer noticeStatus=0;

    private String createTime;

    private String updateTime;
}
