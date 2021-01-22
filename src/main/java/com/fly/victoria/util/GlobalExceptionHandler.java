package com.fly.victoria.util;

import com.fly.victoria.dto.BaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author guoxiang
 * @version 1.0.0
 * @since 2021/1/22
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    public BaseDto<Object> exceptionHandler(Exception e){
        log.error("请求服务error：", e);
        return BaseDto.error(e);
    }

}
