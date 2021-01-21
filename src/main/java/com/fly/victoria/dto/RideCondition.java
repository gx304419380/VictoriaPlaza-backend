package com.fly.victoria.dto;

import com.fly.victoria.util.ConvertUtils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RideCondition {

    private Integer type;
    private Long openId;
    private LocalDateTime rideTime;
    private String orderBy;
    private Boolean isAsc;
    private Integer pageNo;
    private Integer pageSize;

    public String getOrderBy() {
        return ConvertUtils.convertToUnderscore(orderBy);
    }
}
