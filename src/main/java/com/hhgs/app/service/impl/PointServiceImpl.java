package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.PointMapper;
import com.hhgs.app.model.DO.Points;
import com.hhgs.app.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointMapper pointMapper;

    @Override
    public List<Points> queryByType(int type) {
        return pointMapper.queryByType(type);
    }
}
