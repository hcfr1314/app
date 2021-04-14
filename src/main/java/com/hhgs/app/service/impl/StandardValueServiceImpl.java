package com.hhgs.app.service.impl;

import com.hhgs.app.mapper.hh.StandardValueMapper;
import com.hhgs.app.model.DO.StandardValue;
import com.hhgs.app.model.DO.user.Group;
import com.hhgs.app.model.DO.user.User;
import com.hhgs.app.service.StandardValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StandardValueServiceImpl implements StandardValueService {

    @Autowired
    private StandardValueMapper standardValueMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateStandardValue(int plantCode, double val, double val2) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Group> groups = user.getGroups();

        if (groups != null && groups.size() > 0) {
            List<Group> collect = groups.stream().filter(ele -> ele.getGroupid() == plantCode).collect(Collectors.toList());

            if (collect != null && collect.size() > 0) {
                String name = user.getUsername();
                return standardValueMapper.updateStandardValue(plantCode, val, val2, name);
            } else {
                throw new RuntimeException("你没权限修改该场站的数据");
            }
        }

        throw new RuntimeException("你没权限修改该场站的数据");

    }

    @Override
    public List<StandardValue> list() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Group> groups = user.getGroups();
        if (groups != null && groups.size() > 0) {
            List<Integer> codeList = new ArrayList<>();
            groups.forEach(ele -> {
                codeList.add(ele.getGroupid());
            });
            if (codeList.size() > 0) {
                return standardValueMapper.queryByPlantCodeList(codeList);
            }
        }
        return null;
    }

    @Override
    public List<StandardValue> getStandardValueByCode(int plantCode) {
        List<Integer> list = new ArrayList<>();
        list.add(plantCode);
        return standardValueMapper.queryByPlantCodeList(list);
    }
}
