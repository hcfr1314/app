package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.InfluxDBDataObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

@Qualifier("hhTemplate")
public interface InfluxDBDataObjectMapper {
    List<InfluxDBDataObject> queryObjectNameByStationName(@Param("nameList") List<String> nameList);

}
