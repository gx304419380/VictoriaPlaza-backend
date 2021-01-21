package com.fly.victoria.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fly.victoria.entity.Call;
import com.fly.victoria.entity.Ride;
import org.springframework.stereotype.Repository;

@Repository
public interface CallDao extends BaseMapper<Call> {
}
