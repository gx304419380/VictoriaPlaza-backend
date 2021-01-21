package com.fly.victoria.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("tb_ride")
public class Ride {

    @TableId(type = IdType.AUTO)

    private Long id;

    private String content;

    private String destination;

    private String location;

    private Integer direction;

    private String startPoint;

    private Integer type;

    private String username;

    private Long openId;

    private String phone;

    private LocalDateTime rideTime;

    private Double startAddressLatitude;
    private Double startAddressLongitude;
    private String startAddressName;
    private String startAddressAddress;

    private Double endAddressLatitude;
    private Double endAddressLongitude;
    private String endAddressName;
    private String endAddressAddress;

}
