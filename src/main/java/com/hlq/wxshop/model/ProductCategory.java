package com.hlq.wxshop.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author:HLQ
 * @Date:2019/3/4 17:28
 */

@Entity //表明该类UserEntity为一个实体类,默认对应数据库中的表名是user_entity
@DynamicUpdate  //动态跟新，设置默认时间时
@Data   //lombok 去掉get set方法

public class ProductCategory {

    /**
     *  类目id
     */
//    @Id
//    @GeneratedValue
//    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * 类目编号
     */
    @Id
    @GeneratedValue
    private Integer categoryType;

    /**
     * 类目图标
     */
    private String categoryIco;

    private Date createTime;

    private Date updateTime;


    public ProductCategory() {
    }


    public ProductCategory(String categoryName, String categoryIco, Date createTime, Date updateTime) {
        this.categoryName = categoryName;
        this.categoryIco = categoryIco;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
