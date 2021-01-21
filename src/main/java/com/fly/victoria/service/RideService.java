package com.fly.victoria.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fly.victoria.dto.BaseDto;
import com.fly.victoria.dto.PageDto;
import com.fly.victoria.dto.RideCondition;
import com.fly.victoria.dto.RideDto;
import com.fly.victoria.entity.Call;
import com.fly.victoria.entity.Ride;

import java.time.LocalDateTime;
import java.util.List;

public interface RideService extends IService<Ride> {


    PageDto<Ride> searchRide(RideCondition condition);


    List<RideDto> callHistory(String openId, LocalDateTime start);


    void saveCall(Call call);

    void delete(Long id);
}
