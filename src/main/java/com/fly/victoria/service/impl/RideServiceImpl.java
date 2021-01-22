package com.fly.victoria.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fly.victoria.dao.CallDao;
import com.fly.victoria.dao.RideDao;
import com.fly.victoria.dto.BaseDto;
import com.fly.victoria.dto.PageDto;
import com.fly.victoria.dto.RideCondition;
import com.fly.victoria.dto.RideDto;
import com.fly.victoria.entity.Call;
import com.fly.victoria.entity.Ride;
import com.fly.victoria.exception.SaveRideTooMuchException;
import com.fly.victoria.service.RideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class RideServiceImpl extends ServiceImpl<RideDao, Ride> implements RideService {


    private final RideDao rideDao;
    private final CallDao callDao;

    private static final ConcurrentHashMap<Long, AtomicInteger> USER_SAVE_TIME = new ConcurrentHashMap<>();


    public RideServiceImpl(RideDao rideDao, CallDao callDao) {
        this.rideDao = rideDao;
        this.callDao = callDao;
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void cron() {
        log.info("- 定时清缓存，size = {} ......", USER_SAVE_TIME.size());
        USER_SAVE_TIME.clear();
        log.info("- 缓存清空完成......");
    }


    @Override
    public void saveRide(Ride ride) {
        Long openId = ride.getOpenId();
        //这里会有并发问题，不过不要紧
        AtomicInteger count = USER_SAVE_TIME.computeIfAbsent(openId, id -> new AtomicInteger());

        if (count.get() > 5) {
            throw new SaveRideTooMuchException("每日发送拼车信息不能超过5条！");
        }

        count.incrementAndGet();
        saveOrUpdate(ride);
    }

    @Override
    public PageDto<Ride> searchRide(RideCondition condition) {

        Integer type = condition.getType();
        Long openId = condition.getOpenId();
        LocalDateTime rideTime = condition.getRideTime();
        String orderBy = condition.getOrderBy();
        Boolean isAsc = condition.getIsAsc();
        Integer pageNo = condition.getPageNo();
        Integer pageSize = condition.getPageSize();

        QueryWrapper<Ride> wrapper = new QueryWrapper<>();
        wrapper.eq(type != null, "type", type)
                .eq(openId != null, "open_id", openId)
                .ge(rideTime != null, "ride_time", rideTime);

        Page<Ride> page = new Page<>(pageNo, pageSize);

        if (orderBy != null) {
            page.setOrders(List.of(new OrderItem(orderBy, isAsc)));
        }

        Page<Ride> ridePage = rideDao.selectPage(page, wrapper);

        return PageDto.of(ridePage);
    }


    @Override
    public List<RideDto> callHistory(String openId, LocalDateTime start) {

        List<Ride> rideList = rideDao.selectCallHistory(openId, start);

        return rideList.stream().map(RideDto::new).collect(toList());
    }


    @Override
    public void delete(Long id) {
        rideDao.deleteById(id);
    }


    @Override
    public void saveCall(Call call) {
        callDao.insert(call);
    }


}
