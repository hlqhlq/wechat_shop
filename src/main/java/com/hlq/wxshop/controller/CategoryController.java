package com.hlq.wxshop.controller;

import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.model.ProductCategory;
import com.hlq.wxshop.service.CategoryService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/7 18:03
 */
@RestController
@RequestMapping("/wx/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public ResultVO getCategory(){
        List<ProductCategory> category = categoryService.findAll();
        return ResultVOUtil.success(category);
    }
}
