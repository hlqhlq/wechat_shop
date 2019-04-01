package com.hlq.wxshop.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

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
    @NotEmpty
    private  String openid;
    /** 名字 */
    @NotEmpty
    private String addressUsername;
    /** 手机号. */
    @NotEmpty
    private String addressTelnumber;
    /** 邮编 */
    @NotEmpty
    private String addressPostalcode;
    /** 省份*/
    @NotEmpty
    private String addressProvincename;
    /** 城市*/
    @NotEmpty
    private String addressCityname;
    /** 县区 */
    @NotEmpty
    private String addressCountryname;
    /** 具体地址*/
    @NotEmpty
    private String addressDetailinfo;

    /**是否设置为默认地址*/
    private Integer addressDefault=0;




}
