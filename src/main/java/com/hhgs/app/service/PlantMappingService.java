package com.hhgs.app.service;

import java.util.List;

import com.hhgs.app.model.DO.alarm.AlarmPlantMapping;
import org.springframework.security.core.Authentication;

public interface PlantMappingService {

    List<AlarmPlantMapping> getAllPlantMapping(Authentication authentication);

    AlarmPlantMapping getByPlantName(String plantName);
}
