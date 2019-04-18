package com.hlq.wxshop.controller.pc;

import com.hlq.wxshop.VO.ResultVO;
import com.hlq.wxshop.model.SellUser;
import com.hlq.wxshop.service.SellUserService;
import com.hlq.wxshop.utils.MD5Util;
import com.hlq.wxshop.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 页面跳转控制器
 * @Author:HLQ
 * @Date:2019/4/9 20:33
 */
@Controller
@RequestMapping("/pc")
public class IndexController {

    @Autowired
    private SellUserService sellUserService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultVO login(SellUser sellUser, HttpSession session){

        String password=sellUser.getPassword();
        String md5= MD5Util.md5(password);
        SellUser user = sellUserService
                .findByUsernameAndPassword(sellUser.getUsername(), md5);
        if(user!=null){
            //将
            session.setAttribute("currentUser",user);
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(1,"登录失败");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("currentUser");
        return "/index";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/goodsList")
    public String goodsList(){
        return "goods/goodsList";
    }

    @GetMapping("/addGoods")
    public String addGoods(){
        return "goods/addGoods";
    }

    @GetMapping("/orderList")
    public String orderList(){
        return "order/orderList";
    }

    @GetMapping("/categoryList")
    public String categoryList(){
        return "category/categoryList";
    }

    @GetMapping("/carouselList")
    public String carouselList(){
        return "carousel/carouselList";
    }

    @GetMapping("/addCarousel")
    public String addCarousel(){
        return "carousel/addCarousel";
    }

    @GetMapping("/noticeList")
    public String noticeList(){
        return "notice/noticeList";
    }

    @GetMapping("/editUser")
    public String editUser(){
        return "user/editUser";
    }
}
