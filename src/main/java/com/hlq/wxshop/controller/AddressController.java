package com.hlq.wxshop.controller;

import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.enums.AddressStatusEnum;
import com.hlq.wxshop.model.UserAddress;
import com.hlq.wxshop.service.AddressService;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/25 18:58
 */
@RestController
@RequestMapping("/wx/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/save")
    public ResultVO saveAddress(UserAddress userAddress){
        UserAddress address = addressService.save(userAddress);
        return ResultVOUtil.success(address);
    }

    /**
     * 根据id跟新默认地址
     */
    @GetMapping("/update")
    public ResultVO updateAddress(Integer id,Integer isDefault,String openid){
        List<UserAddress> addressList = addressService.findByOpenidIn(openid);
        for(UserAddress address:addressList){
            if(address.getAddressId().equals(id)){
                address.setAddressDefault(isDefault);
                addressService.save(address);
            }else{
                address.setAddressDefault(AddressStatusEnum.notDefault.getCode());
                addressService.save(address);
            }
        }
        return ResultVOUtil.success();
    }

    @GetMapping("/findByOpenid")
    public ResultVO findById(String  openid){
        List<UserAddress> addressList = addressService.findByOpenidIn(openid);
        return ResultVOUtil.success(addressList);
    }

    @GetMapping("/deleteById")
    public ResultVO delete(Integer id){
        addressService.deleteById(id);
        return  ResultVOUtil.success();
    }
}
