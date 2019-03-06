package com.hlq.wxshop.controller;

import com.hlq.wxshop.VO.ProductVO;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.model.ProductCategory;
import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.service.CategoryService;
import com.hlq.wxshop.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:HLQ
 * @Date:2019/3/6 15:21
 */
@RestController
@RequestMapping("/wx/productInfo")
public class ProductInfoController {
    
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/list")
    public ResultVO list(){
        //1. 查询所有的上架商品
        List<ProductInfo> productList = productInfoService.findOnAll();
        //2. 查询类目(一次性查询)  精简方法(java8, lambda)
        List<Integer> categoryTypeList = productList.stream().map(e -> e.getCategoryType()).
                collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();

        return null;
    }
}
