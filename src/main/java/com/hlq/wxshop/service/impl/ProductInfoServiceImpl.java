package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.dao.ProductInfoDao;
import com.hlq.wxshop.enums.ProductStatusEnum;
import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/6 11:36
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao dao;
    @Override
    public ProductInfo findOne(String productId) {
        return dao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findOnAll() {
        return dao.findByProductStatus(ProductStatusEnum.On.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return dao.save(productInfo);
    }
}
