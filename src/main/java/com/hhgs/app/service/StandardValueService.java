package com.hhgs.app.service;

import com.hhgs.app.model.DO.StandardValue;

import java.util.List;

public interface StandardValueService {

    int updateStandardValue(int plantCode, double val, double val2);

    List<StandardValue> list();

    List<StandardValue> getStandardValueByCode(int plantCode);
}
