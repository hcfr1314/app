package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.PlantInfoMapper;
import com.hhgs.app.model.DO.PlantInfo;
import com.hhgs.app.model.DO.user.Group;
import com.hhgs.app.model.DO.user.Role;
import com.hhgs.app.model.DO.user.User;
import com.hhgs.app.service.PlantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlantInfoServiceImpl  implements PlantInfoService {

    @Autowired
    private PlantInfoMapper plantInfoMapper;

    @Override
    public List<PlantInfo> getPlantList(int plantType, Authentication authentication) {
        User user=(User)authentication.getPrincipal();
        List<Group> groups=user.getGroups();
        //如果是admin用户，则不需要加此条件
        List<Role> list= user.getRoles().stream().filter(ele->{return "admin".equals(ele.getName());}).collect(Collectors.toList());
        if(list.size()!=0){
            return plantInfoMapper.getPlantInfoList(plantType,null);
        }

        return plantInfoMapper.getPlantInfoList(plantType,groups);

    }

    @Override
    public Map<Integer, List<PlantInfo>> getAllList() {
        List<PlantInfo> allList = plantInfoMapper.getAllList();
        return allList.stream().collect(Collectors.groupingBy(PlantInfo::getPlantCode));
    }

}
