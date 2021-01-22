package com.fly.victoria.controller;

import com.fly.victoria.dto.BaseDto;
import com.fly.victoria.dto.PageDto;
import com.fly.victoria.dto.RideCondition;
import com.fly.victoria.dto.RideDto;
import com.fly.victoria.entity.Call;
import com.fly.victoria.entity.Ride;
import com.fly.victoria.service.RideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("ride")
@Slf4j
public class RideController {

    @Autowired
    private RideService rideService;


    @PostMapping("page")
    public BaseDto<PageDto<RideDto>> rideList(@RequestBody RideCondition condition) {
        PageDto<Ride> pageDto = rideService.searchRide(condition);

        PageDto<RideDto> dto = new PageDto<>(pageDto, RideDto::new);

        return new BaseDto<>(dto);
    }

    @PostMapping
    public BaseDto<Ride> save(@RequestBody RideDto rideDto) {

        log.info("save ride: {}", rideDto);
        Ride ride = rideDto.convertTo();

        log.info("now = {}", OffsetDateTime.now());

        rideService.saveRide(ride);
        return new BaseDto<>(ride);
    }

    @PostMapping("delete/{id}")
    public BaseDto<Object> delete(@PathVariable Long id) {
        rideService.delete(id);

        return new BaseDto<>(null);
    }

    @GetMapping("callHistory")
    public BaseDto<List<RideDto>> callHistory(@RequestParam String openId) {
        LocalDateTime oneWeek = LocalDate.now().minusDays(7).atStartOfDay();
        List<RideDto> list = rideService.callHistory(openId, oneWeek);

        return new BaseDto<>(list);
    }

    @PostMapping("call")
    public BaseDto<Call> saveCall(@RequestBody Call call) {

        log.info("save call: {}", call);
        rideService.saveCall(call);

        return new BaseDto<>(call);
    }
}
