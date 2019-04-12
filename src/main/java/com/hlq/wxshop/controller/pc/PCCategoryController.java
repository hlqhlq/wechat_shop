package com.hlq.wxshop.controller.pc;

import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.model.ProductCategory;
import com.hlq.wxshop.service.CategoryService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:HLQ
 * @Date:2019/4/12 22:55
 */
@RestController
@RequestMapping("/pc/category")
public class PCCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/update")
    public ResultVO update(ProductCategory productCategory){

        ProductCategory category = categoryService.save(productCategory);
        return ResultVOUtil.success(category);
    }
}
