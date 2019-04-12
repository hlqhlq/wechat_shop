package com.hlq.wxshop.controller.pc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.config.LimitGlobalData;
import com.hlq.wxshop.model.ProductInfo;
import com.hlq.wxshop.service.ProductInfoService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/4/11 23:16
 */
@RestController
@RequestMapping("/pc/product")
public class PCProductController {

    @Autowired
    private LimitGlobalData limitGlobalData;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 返回给echart图标的数据
     * @return
     */
    @GetMapping("/findGoodsByVolume")
    public ResultVO findGoodsByVolume(){
        List<ProductInfo> list = productInfoService.findGoodsByVolume();
        String[] name=new String[limitGlobalData.getProductNum()];
        Integer[] volume=new Integer[limitGlobalData.getProductNum()];
        for(int i=0;i<list.size();i++){
            name[i]=list.get(i).getProductName();
            volume[i]=list.get(i).getProductVolume();
        }
        JSONObject obj=new JSONObject();
        obj.put("name", name);
        obj.put("volume", volume);
        return ResultVOUtil.success(obj);
    }

    @GetMapping("/findMostHotsGoodsByHits")
    public ResultVO findMostHotsGoodsByHits(){
        List<ProductInfo> list = productInfoService.findMostHotsGoodsByHits();
        JSONArray array=new JSONArray();
        for(ProductInfo info:list){
            JSONObject obj=new JSONObject();
            obj.put("name",info.getProductName());
            obj.put("num",info.getProductHits());
            array.add(obj);
        }
        return  ResultVOUtil.success(array);
    }

    @GetMapping("/findBySplitPage")
    public JSONObject findBySplitPage(Integer page, Integer limit){
        Pageable request=new PageRequest(page-1,limit);
        Page<ProductInfo> info = productInfoService.findAll(request);
        System.out.println(info.getContent());
        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", info.getTotalElements());
        obj.put("data", info.getContent());
        return obj;
    }

    @PostMapping("/update")
    public ResultVO update(ProductInfo productInfo){
        ProductInfo update = productInfoService.update(productInfo);
        return ResultVOUtil.success(update);
    }


}
