package com.hlq.wxshop.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.model.ProductCategory;
import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.service.CategoryService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author:HLQ
 * @Date:2019/4/12 22:55
 */
@RestController
@RequestMapping("/pc/category")
public class PCCategoryController {

    @Autowired
    private CategoryService categoryService;

//    @PostMapping("/update")
//    public ResultVO update(ProductCategory productCategory){
//
//        ProductCategory category = categoryService.save(productCategory);
//        return ResultVOUtil.success(category);
//    }

    @GetMapping("/findBySplitPage")
    public JSONObject findBySplitPage(Integer page,Integer limit){
        Pageable request = new PageRequest(page - 1, limit);
        Page<ProductCategory> info  = categoryService.findAll(request);
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", info.getTotalElements());
        obj.put("data", info.getContent());
        return obj;
    }

    @GetMapping("/putOn")
    public ResultVO putOn(Integer categoryType){
        ProductCategory category = categoryService.putOn(categoryType);
        return ResultVOUtil.success(category);
    }

    @GetMapping("/takeOff")
    public ResultVO takeOff(Integer categoryType){
        ProductCategory category = categoryService.takeOff(categoryType);
        return ResultVOUtil.success(category);
    }

    @PostMapping("/update")
    public ResultVO update(ProductCategory productCategory){
        ProductCategory one = categoryService.findOne(productCategory.getCategoryType());
        one.setCategoryName(productCategory.getCategoryName());
        one.setCategoryIco(productCategory.getCategoryIco());
        one.setUpdateTime(new Date());
        ProductCategory save = categoryService.save(one);
        return ResultVOUtil.success(save);
    }
}
