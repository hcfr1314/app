package com.hhgs.app.service;

import com.hhgs.app.model.DO.hbase.HbaseObject;

import java.util.List;
import java.util.Map;

public interface HbaseObjectService {

    /**
     * 查询所有的hbase数据对象
     *
     * @return
     */
    Map<Integer, List<HbaseObject>> queryAllHbaseObject();

}
