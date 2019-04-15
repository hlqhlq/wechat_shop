package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.config.LimitGlobalData;
import com.hlq.wxshop.dao.ProductInfoDao;
import com.hlq.wxshop.dto.CartDTO;
import com.hlq.wxshop.enums.ProductStatusEnum;
import com.hlq.wxshop.enums.ResultEnum;
import com.hlq.wxshop.exception.SellException;
import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.service.ProductInfoService;
import com.hlq.wxshop.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:HLQ
 * @Date:2019/3/6 11:36
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {


    /**
     * 自定义属性值
     */
    @Autowired
    private LimitGlobalData limitGlobalData;

    @Autowired
    private ProductInfoDao dao;

    @Override
    public ProductInfo findOne(String productId) {
        return dao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findByCategoryTypeIn(Integer categoryType) {
        return dao.findByCategoryTypeIn(categoryType);
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
    @Transactional(rollbackFor =Exception.class)
    public ProductInfo save(ProductInfo productInfo) {
        return dao.save(productInfo);
    }

    @Override
    @Transactional(rollbackFor =Exception.class )
    public void addStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = dao.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            dao.save(productInfo);
        }

    }

    @Override
    @Transactional(rollbackFor =Exception.class )
    public void addVolume(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = dao.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductVolume() + cartDTO.getProductQuantity();
            productInfo.setProductVolume(result);
            dao.save(productInfo);
        }
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public void decVolume(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = dao.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductVolume()-cartDTO.getProductQuantity();
            productInfo.setProductVolume(result);
            dao.save(productInfo);
        }
    }

    @Override
    @Transactional(rollbackFor =Exception.class )
    public void decStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo info = dao.findOne(cartDTO.getProductId());
            if(info == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = info.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            info.setProductStock(result);
            dao.save(info);
        }
    }

    @Override
    public List<ProductInfo> findGoodsByVolume() {
        List<ProductInfo> list = dao.findGoodsByVolume(limitGlobalData.getProductNum());
        return  list;
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public ProductInfo updateHits(String productId) {
        ProductInfo product = dao.findOne(productId);
        AtomicInteger hits=new AtomicInteger(product.getProductHits());
        //保证多线程安全
        product.setProductHits(hits.incrementAndGet());
        ProductInfo result = dao.save(product);
        return  result;
    }

    @Override
    public List<ProductInfo> findMostHotsGoodsByHits() {
        List<ProductInfo> info = dao.findMostHotsGoodsByHits(limitGlobalData.getHitsNum());
        return info;
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public ProductInfo update(ProductInfo productInfo) {
        ProductInfo info = dao.findOne(productInfo.getProductId());
        productInfo.setProductVolume(info.getProductVolume());
        productInfo.setProductHits(info.getProductHits());
        productInfo.setCreateTime(info.getCreateTime());
        productInfo.setUpdateTime(new Date());
        ProductInfo product = dao.save(productInfo);
        return product;
    }


    @Override
    @Transactional(rollbackFor =Exception.class)
    public ProductInfo takeOff(String productId) {
        ProductInfo productInfo = dao.findOne(productId);
        productInfo.setProductStatus(ProductStatusEnum.Down.getCode());
        productInfo.setUpdateTime(new Date());
        ProductInfo result = dao.save(productInfo);
        return result;
    }

    @Override
    @Transactional(rollbackFor =Exception.class)
    public ProductInfo putOn(String productId) {
        ProductInfo productInfo = dao.findOne(productId);
        productInfo.setProductStatus(ProductStatusEnum.On.getCode());
        productInfo.setUpdateTime(new Date());
        ProductInfo result = dao.save(productInfo);
        return result;
    }

    @Override
    public Page<ProductInfo> searchByKey(String productId, String productName, Integer categoryType, Pageable pageable) {
        return dao.searchByKey(productId,productName,categoryType,pageable);
    }
}
