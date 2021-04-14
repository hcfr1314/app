package com.hhgs.app.service;

import com.hhgs.app.model.DO.Points;

import java.util.List;

public interface PointService {

    List<Points> queryByType(int type);
}
