package com.fly.victoria.controller;

import com.fly.victoria.dto.BaseDto;
import com.fly.victoria.dto.PageDto;
import com.fly.victoria.dto.RideDto;
import com.fly.victoria.entity.Ride;
import com.fly.victoria.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("ride")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping
    public BaseDto<PageDto<RideDto>> rideList(Integer type,
                                           String openId,
                                           LocalDateTime rideTime,
                                           String orderColumn,
                                           Boolean isAsc,
                                           Integer pageNo,
                                           Integer pageSize) {
        PageDto<Ride> pageDto = rideService.searchRide(type, openId, rideTime, orderColumn, isAsc, pageNo, pageSize);


        PageDto<RideDto> dto = new PageDto<>(pageDto, RideDto::new);

        return new BaseDto<>(dto);
    }

    @PostMapping
    public BaseDto<Ride> save(@RequestBody RideDto rideDto) {

        Ride ride = rideDto.convertTo();

        rideService.save(ride);
        return new BaseDto<>(ride);
    }

}
