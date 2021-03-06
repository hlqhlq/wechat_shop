package com.hlq.wxshop.controller;

import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.enums.AddressStatusEnum;
import com.hlq.wxshop.enums.ResultEnum;
import com.hlq.wxshop.exception.SellException;
import com.hlq.wxshop.model.UserAddress;
import com.hlq.wxshop.service.AddressService;
import com.hlq.wxshop.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author:HLQ
 * @Date:2019/3/25 18:58
 */
@RestController
@RequestMapping("/wx/address")
@Slf4j
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/save")
    public ResultVO saveAddress(@Valid UserAddress userAddress, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
              log.error("【保存地址】参数不正确，userAddress={}",userAddress);
              throw new SellException(ResultEnum.PARAM_ERROR);
        }
        UserAddress address = addressService.save(userAddress);
        return ResultVOUtil.success(address);
    }

    /**
     * 根据id更新默认地址
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

    @GetMapping("/findOne")
    public ResultVO findOne(Integer id){
        UserAddress address = addressService.findById(id);
        return ResultVOUtil.success(address);
    }

    /**
     * 查询登录用户的默认的地址，即addressDefault为1
     * @return
     */
    @GetMapping("/findByDefault")
    public ResultVO findByDefault(String openid){
        UserAddress address = addressService.findByAddressDefaultEqualsAndOpenid
                (AddressStatusEnum.isDefault.getCode(),openid);
        return ResultVOUtil.success(address);
    }
}
