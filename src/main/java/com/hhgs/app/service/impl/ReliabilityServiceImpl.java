package com.hhgs.app.service.impl;

import com.hhgs.app.model.DO.PowerIndicator;
import com.hhgs.app.model.DO.StandardValue;
import com.hhgs.app.service.PowerIndicatorService;
import com.hhgs.app.service.ReliabilityService;
import com.hhgs.app.service.StandardValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReliabilityServiceImpl implements ReliabilityService {

    @Autowired
    private PowerIndicatorService powerIndicatorService;

    @Autowired
    private StandardValueService standardValueService;

    @Override
    public List<PowerIndicator> hour(int plantCode, String date) {
        List<PowerIndicator> hourList = powerIndicatorService.hour(plantCode, date);
        return hourList;
    }

    @Override
    public  Map<String, Object> rate(int plantCode, String date) {
        Map<String, Object> result = new HashMap<>();

        //查询标准线
        List<StandardValue> standardValList = standardValueService.getStandardValueByCode(plantCode);
        if(standardValList!=null && standardValList.size()>0) {
            result.put("standardVal", standardValList.get(0));
        }
        //查询传入日期的可利用率
        return null;
    }
}
