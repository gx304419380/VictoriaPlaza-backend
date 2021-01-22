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

    public static <T> BaseDto<T> error(Exception e) {
        BaseDto<T> dto = new BaseDto<>(null);
        dto.setMsg(e.getMessage());
        dto.setCode(400);
        return dto;
    }
}
