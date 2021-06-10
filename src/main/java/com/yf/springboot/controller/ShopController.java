package com.yf.springboot.controller;


import com.yf.springboot.entity.Shop;
import com.yf.springboot.service.IShopService;
import com.yf.springboot.utils.RedisUtil;
import com.yf.springboot.vo.CommonResult;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private IShopService shopService;

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/show")
    public CommonResult show(HttpServletRequest request){
        String username = request.getHeader("token");
        System.out.println(username);
        boolean flag = redisUtil.hasKey(username);
        if(flag){
            return new CommonResult(200,"查询成功",shopService.list());
        }else{
            return new CommonResult(500,"查询失败");
        }
    }

    @CrossOrigin
    @GetMapping("/show2")
    public List<Shop> show2(){
        return shopService.list();
    }

}
