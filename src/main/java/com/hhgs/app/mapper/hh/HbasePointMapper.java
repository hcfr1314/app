package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.hbase.HbasePoint;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HbasePointMapper {

    List<HbasePoint> queryAllHbasePoint();
}
