package com.zhang.sell.service;

import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.alibaba.ocean.rawsdk.client.entity.AuthorizationToken;
import com.alibaba.ocean.rawsdk.common.AbstractAPIRequest;
import com.zhang.sell.constants.AppConstants;
import com.zhang.sell.excepton.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;


/**
 * @Auther: Administrator
 * @Date: 2018/08/30 12:19
 * @Description:
 */
public abstract class BaseService extends AppConstants {

    private final Logger log = LoggerFactory.getLogger(BaseService.class);

    protected static final ApiExecutor apiExecutor = new ApiExecutor(SYS_APP_KEY, SYS_SEC_KEY);

    @Autowired
    private HttpSession session;

    /**
     * 从Session中获取AccessToken
     *
     * @return AccessToken
     */
    private final String getAccessToken() {
        AuthorizationToken userToken = getAuth();
        // 判断AccessToken是否过期
        if (userToken.getExpires_in() >= System.currentTimeMillis()) {
            userToken = refreshAccessToken();
        }
        return userToken.getAccess_token();
    }

    /**
     * 保存token对象到session中
     *
     * @param token
     */
    protected final void AddAuthorizationToken(final AuthorizationToken token) {
        session.setAttribute("USER_TOKEN", token);
    }

    /**
     * 从Session中获取RefreshToken
     *
     * @return RefreshToken
     */
    private final String getRefreshToken() throws AppException {
        AuthorizationToken userToken = getAuth();
        if (userToken.getRefresh_token_timeout().getTime() >= System.currentTimeMillis()) {
            throw new AppException("授权过期，重新授权", 10000);
        }
        return userToken.getRefresh_token();
    }

    /**
     * 刷新 AccessToken
     *
     * @return AuthorizationToken
     */
    private AuthorizationToken refreshAccessToken() {
        AuthorizationToken authorizationToken = null;
        try {
            authorizationToken = apiExecutor.refreshToken(getRefreshToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保存到session中
        AddAuthorizationToken(authorizationToken);
        return authorizationToken;
    }

    public final <TResponse> TResponse executeOfAccessToken(AbstractAPIRequest<TResponse> apiRequest){
        return apiExecutor.execute(apiRequest,getAccessToken());
    }

    public final <TResponse> TResponse execute(AbstractAPIRequest<TResponse> apiRequest){
        return apiExecutor.execute(apiRequest);
    }
    public AuthorizationToken getAuth(){
        return (AuthorizationToken) session.getAttribute("USER_TOKEN");
    }
}
