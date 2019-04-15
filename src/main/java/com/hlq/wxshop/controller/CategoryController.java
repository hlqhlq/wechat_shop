package com.hlq.wxshop.controller;

import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.enums.CategoryStatusEnum;
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

    /**
     * 查询开启状态的类目
     * @return
     */
    @GetMapping("/findStatusOn")
    public ResultVO findStatusOn(){
        List<ProductCategory> list = categoryService.findByStatus(CategoryStatusEnum.ON.getCode());
        return ResultVOUtil.success(list);
    }
}
