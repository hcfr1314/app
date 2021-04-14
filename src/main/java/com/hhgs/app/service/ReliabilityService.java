package com.hhgs.app.service;

import com.hhgs.app.model.DO.PowerIndicator;

import java.util.List;
import java.util.Map;

public interface ReliabilityService {
    List<PowerIndicator> hour(int plantCode, String date);

    Map<String, Object> rate(int plantCode, String date);
}
