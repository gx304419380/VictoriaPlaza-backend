package com.fly.victoria.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fly.victoria.dto.BaseDto;
import com.fly.victoria.dto.RideDto;
import com.fly.victoria.entity.Ride;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RideDao extends BaseMapper<Ride> {

    @Select("select b.* from tb_call a, tb_ride b where a.ride_id = b.id " +
            "and a.open_id=#{openId} " +
            "and a.ride_time > #{start}")
    List<Ride> selectCallHistory(String openId, LocalDateTime start);
}
