package com.zhang.sell.service;


import com.alibaba.product.param.AlibabaProductListGetParam;
import com.alibaba.product.param.AlibabaProductListGetResult;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator
 * @Date: 2018/09/01 17:48
 * @Description:
 */
@Service
public class ProductService extends BaseService {
    public AlibabaProductListGetResult findList(AlibabaProductListGetParam param){
        return executeOfAccessToken(param);
    }
}
