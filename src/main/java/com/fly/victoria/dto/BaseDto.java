package com.fly.victoria.dto;

import lombok.Data;

@Data
public class BaseDto<T> {

    private T data;

    private String msg;

    private Integer code = 200;

    public BaseDto(T data) {
        this.data = data;
    }
}
