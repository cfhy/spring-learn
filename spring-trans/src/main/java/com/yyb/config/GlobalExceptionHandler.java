package com.yyb.config;

import com.yyb.common.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Created by Administrator on 2017/11/7.
 * 统一异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    public ResponseData exception(Exception e) {
        logger.info("Exception",e);
        return ResponseData.fail("服务器内部错误，请重试");
    }
}
