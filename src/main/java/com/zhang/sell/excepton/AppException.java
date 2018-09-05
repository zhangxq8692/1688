package com.zhang.sell.excepton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Auther: Administrator
 * @Date: 2018/08/22 15:47
 * @Description:
 */
public class AppException extends Exception {
    private Logger logger = LoggerFactory.getLogger(AppException.class);
    /**
     * 错误代码
     */
    private Integer errorCode;

    public AppException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
        logger.info(message, this);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
