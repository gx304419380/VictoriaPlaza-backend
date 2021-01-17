package com.fly.victoria.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("tb_call")
public class Call {

    @TableId(type = IdType.AUTO)
    Long id;

    LocalDateTime creatTime;

    LocalDateTime updateTime;

    String openId;

    String rideId;

    LocalDateTime rideTime;

}
