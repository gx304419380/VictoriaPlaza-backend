package com.fly.victoria.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fly.victoria.dao.CallDao;
import com.fly.victoria.dao.RideDao;
import com.fly.victoria.dto.PageDto;
import com.fly.victoria.entity.Ride;
import com.fly.victoria.service.RideService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class RideServiceImpl extends ServiceImpl<RideDao, Ride> implements RideService {


    private final RideDao rideDao;
    private final CallDao callDao;


    public RideServiceImpl(RideDao rideDao, CallDao callDao) {
        this.rideDao = rideDao;
        this.callDao = callDao;
    }


    @Override
    public PageDto<Ride> searchRide(Integer type,
                                    String openId,
                                    LocalDateTime rideTime,
                                    String orderColumn,
                                    Boolean isAsc,
                                    Integer pageNo,
                                    Integer pageSize) {

        QueryWrapper<Ride> wrapper = new QueryWrapper<>();
        wrapper.eq(type != null, "type", type)
                .eq(openId != null, "open_id", openId)
                .ge(rideTime != null, "ride_time", rideTime);

        Page<Ride> page = new Page<>(pageNo, pageSize);

        if (orderColumn != null) {
            page.setOrders(List.of(new OrderItem(orderColumn, isAsc)));
        }

        Page<Ride> ridePage = rideDao.selectPage(page, wrapper);

        return PageDto.of(ridePage);
    }
}
