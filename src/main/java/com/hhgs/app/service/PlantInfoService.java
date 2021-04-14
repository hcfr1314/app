package com.hhgs.app.service;

import com.hhgs.app.model.DO.PlantInfo;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

public interface PlantInfoService {

    List<PlantInfo> getPlantList(int plantType, Authentication authentication);

    Map<Integer, List<PlantInfo>> getAllList();
}
