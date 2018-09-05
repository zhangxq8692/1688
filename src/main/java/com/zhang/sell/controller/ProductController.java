package com.zhang.sell.controller;

import com.alibaba.product.param.AlibabaProductListGetParam;
import com.alibaba.product.param.AlibabaProductListGetResult;
import com.zhang.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Administrator
 * @Date: 2018/09/01 17:42
 * @Description:
 */
@RestController
@RequestMapping("/product")
public class ProductController{
    @Autowired
    private ProductService service;
    @RequestMapping("/list")
    public AlibabaProductListGetResult findList(AlibabaProductListGetParam param){
        return service.findList(param);
    }
}
