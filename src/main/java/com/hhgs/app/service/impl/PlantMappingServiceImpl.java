package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.PlantMappingMapper;
import com.hhgs.app.model.DO.alarm.AlarmPlantMapping;
import com.hhgs.app.model.DO.user.User;
import com.hhgs.app.service.PlantMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlantMappingServiceImpl implements PlantMappingService {

    @Autowired
    private PlantMappingMapper mapper;

    @Override
    public AlarmPlantMapping getByPlantName(String plantName) {
        return mapper.getByPlantName(plantName);
    }

    @Override
    public List<AlarmPlantMapping> getAllPlantMapping(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Integer> idList = new ArrayList<>();
        user.getGroups().forEach(ele -> {
            idList.add(ele.getGroupid());
        });

        if (idList.size() > 0) {
            return mapper.getAllPlantMapping(idList);
        }
        return null;
    }

}
