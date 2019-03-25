package com.hlq.wxshop.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author:HLQ
 * @Date:2019/3/25 18:32
 */
@Entity
@Data
public class UserAddress {
    @Id
    @GeneratedValue
    private Integer addressId;
    /** 用户openid */
    private  String openid;
    /** 名字 */
    private String addressUsername;
    /** 手机号. */
    private String addressTelnumber;
    /** 邮编 */
    private String addressPostalcode;
    /** 省份*/
    private String addressProvincename;
    /** 城市*/
    private String addressCityname;
    /** 县区 */
    private String addressCountryname;
    /** 具体地址*/
    private String addressDetailinfo;

    /**是否设置为默认地址*/
    private Integer addressDefault=0;




}
