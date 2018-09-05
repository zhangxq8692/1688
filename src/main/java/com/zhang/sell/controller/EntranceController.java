package com.zhang.sell.controller;

import com.zhang.sell.excepton.AppException;
import com.zhang.sell.service.EntranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Administrator
 * @Date: 2018/08/30 12:16
 * @Description:    https://auth.1688.com/oauth/authorize?client_id=4002198&site=1688&redirect_uri=http://112.195.158.58:60000/entrance
 * 用户名：alitestforisv01 密码：Test1234,./
 * alitestforisv02/1234test
 * alitestforisv03/123!@#test
 * alitestforisv04/123!@#test
 */
@Controller
public class EntranceController {
    @Autowired
    private EntranceService service;
    @RequestMapping("entrance")
    public String Entrance(@RequestParam("code") String code){
        try {
            service.getToken(code);
        } catch (AppException appExcepton) {
            appExcepton.printStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping("")
    private String index(HttpServletRequest request){
        request.setAttribute("user",service.getAuth());
        return "/index";
    }
}
