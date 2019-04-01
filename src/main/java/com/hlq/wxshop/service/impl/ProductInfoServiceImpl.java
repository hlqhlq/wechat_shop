package com.hlq.wxshop.service.impl;

import com.hlq.wxshop.dao.ProductInfoDao;
import com.hlq.wxshop.dto.CartDTO;
import com.hlq.wxshop.enums.ProductStatusEnum;
import com.hlq.wxshop.enums.ResultEnum;
import com.hlq.wxshop.exception.SellException;
import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
