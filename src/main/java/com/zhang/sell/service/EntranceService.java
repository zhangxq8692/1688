package com.zhang.sell.service;

import com.alibaba.ocean.rawsdk.client.entity.AuthorizationToken;
import com.zhang.sell.excepton.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Auther: Administrator
 * @Date: 2018/08/30 12:21
 * @Description:
 */
@Service
public class EntranceService extends BaseService{
    private static Logger log = LoggerFactory.getLogger(EntranceService.class);
    public void getToken(String code) throws AppException {
        if (StringUtils.isEmpty(code)){
            throw new AppException("code 参数为空",10001);
        }
        try {
            AuthorizationToken token = apiExecutor.getToken(code);
            // 保存到session中
            AddAuthorizationToken(token);
        } catch (Exception e) {
            throw new AppException("code 已失效",10002);
        }
    }
}
