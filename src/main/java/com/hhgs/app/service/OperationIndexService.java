package com.hhgs.app.service;


import com.hhgs.app.model.DO.PowerIndicator;
import com.hhgs.app.model.VO.OperationIndex;

public interface OperationIndexService {


    /**
     * 查询电能指标
     * @param plantCode
     * @return
     */
    PowerIndicator getIndexValueFromElectrivityIndex(int plantCode);

    OperationIndex getDateByTypeAndCode(int plantCode, int plantType,String token);
}
