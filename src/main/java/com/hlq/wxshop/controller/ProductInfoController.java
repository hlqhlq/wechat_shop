package com.hlq.wxshop.controller;

import com.hlq.wxshop.VO.ProductInfoVO;
import com.hlq.wxshop.VO.ProductVO;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.model.ProductCategory;
import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.service.CategoryService;
import com.hlq.wxshop.service.ProductInfoService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    /**
     * id查询商品
     * @param productId
     * @return
     */
    @GetMapping("/getProductById")
    public ResultVO getProductById(@RequestParam(value="id")String productId){
        ProductInfo info = productInfoService.findOne(productId);
        ProductInfoVO productInfoVO = new ProductInfoVO();
        BeanUtils.copyProperties(info,productInfoVO);
        return ResultVOUtil.success(productInfoVO);
    }

    /**
     * 根据类目查商品
     * @param ctype
     * @return
     */
    @GetMapping("/getProductByType")
    public ResultVO getProductByType(@RequestParam(value="ctype")Integer ctype){
        List<ProductInfo> info = productInfoService.findByCategoryTypeIn(ctype);
        List<ProductInfoVO> productInfoVOList = new ArrayList<>();
        for(ProductInfo productInfo:info){
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(productInfo,productInfoVO);
            productInfoVOList.add(productInfoVO);
        }
        return ResultVOUtil.success(productInfoVOList);
    }
    /**
     * 微信前端首页分类及对应的商品列表
     * @return
     */
    @GetMapping("/index_list")
    public ResultVO list(){
        //1. 查询所有的上架商品
        List<ProductInfo> productList = productInfoService.findOnAll();
        //2. 查询类目(一次性查询)  精简方法(java8, lambda)
        List<Integer> categoryTypeList = productList.stream().map(e -> e.getCategoryType()).
                collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory category:productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:productList){
                //判断分类是否相等
                 if(productInfo.getCategoryType().equals(category.getCategoryType())){
                     ProductInfoVO productInfoVO = new ProductInfoVO();
                     BeanUtils.copyProperties(productInfo,productInfoVO);
                     productInfoVOList.add(productInfoVO);
                 }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

    @GetMapping("/updateHits")
    public ResultVO updateHits(String productId){
        ProductInfo info = productInfoService.updateHits(productId);
        return ResultVOUtil.success(info);
    }
}
