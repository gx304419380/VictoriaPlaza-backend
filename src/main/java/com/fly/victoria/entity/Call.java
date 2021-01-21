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
    private Long id;

    private LocalDateTime creatTime;

    private LocalDateTime updateTime;

    private Long openId;

    private Long rideId;

    private LocalDateTime rideTime;

}
