package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.hbase.HbaseObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HbaseObjectMapper {

    List<HbaseObject> queryAllHbaseObject();
}
