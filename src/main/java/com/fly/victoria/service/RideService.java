package com.fly.victoria.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fly.victoria.dto.PageDto;
import com.fly.victoria.entity.Ride;

import java.time.LocalDateTime;

public interface RideService extends IService<Ride> {


    PageDto<Ride> searchRide(Integer type,
                             String openId,
                             LocalDateTime rideTime,
                             String orderColumn,
                             Boolean isAsc,
                             Integer pageNo,
                             Integer pageSize);


}
