package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.HbaseObjectMapper;
import com.hhgs.app.model.DO.hbase.HbaseObject;
import com.hhgs.app.service.HbaseObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HbaseObjectServiceImpl implements HbaseObjectService {

    @Autowired
    private HbaseObjectMapper hbaseObjectMapper;

    @Override
    public Map<Integer, List<HbaseObject>> queryAllHbaseObject() {
        List<HbaseObject> hbaseObjects = hbaseObjectMapper.queryAllHbaseObject();
        return hbaseObjects.stream().collect(Collectors.groupingBy(HbaseObject::getPlantCode));
    }
}
