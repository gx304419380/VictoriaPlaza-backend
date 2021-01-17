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
    Long id;

    String content;

    String destination;

    String location;

    Integer direction;

    String startPoint;

    Integer type;

    String username;

    String openId;

    String phone;

    LocalDateTime rideTime;

    Double startAddressLatitude;
    Double startAddressLongitude;
    String startAddressName;
    String startAddressAddress;

    Double endAddressLatitude;
    Double endAddressLongitude;
    String endAddressName;
    String endAddressAddress;

}
