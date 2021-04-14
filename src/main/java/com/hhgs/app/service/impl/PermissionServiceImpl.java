package com.hhgs.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhgs.app.mapper.hh.PermissionMapper;
import com.hhgs.app.model.DO.user.Permission;
import com.hhgs.app.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public PageInfo<Permission> findAllPermission(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Permission> permissionList = permissionMapper.findAllPermission();
        PageInfo pageInfo = new PageInfo(permissionList);
        return pageInfo;
    }

    @Override
    @Transactional
    public void savePermission(Permission permission) {
        permissionMapper.savePermission(permission);
    }

    @Override
    @Transactional
    public void deleteByPermissionId(int permissionId) {
        permissionMapper.deleteByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public void editPermission(Permission permission) {
        permissionMapper.editPermission(permission);
    }
}
