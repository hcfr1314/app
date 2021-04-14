package com.hhgs.app.service;

import com.hhgs.app.model.DO.hbase.HbasePoint;

import java.util.List;
import java.util.Map;

public interface HbasePointService {

    Map<Integer, Map<Integer, List<HbasePoint>>> getIdByPlantCodeAndIndexId();

}
