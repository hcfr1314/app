package com.hhgs.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhgs.app.mapper.hh.RoleMapper;
import com.hhgs.app.model.DO.user.Permission;
import com.hhgs.app.model.DO.user.Role;
import com.hhgs.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> loadRoleByRoleName(String roleName) {
        List<Role> roleList = roleMapper.loadRoleByRoleName(roleName);
        return roleList;
    }

    @Override
    public PageInfo<Role> findAllRole(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roleList = roleMapper.findAllRole();
        PageInfo pageInfo = new PageInfo(roleList);
        return pageInfo;
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleMapper.saveRole(role);
    }

    @Override
    @Transactional
    public void editRole(Role role) {
        roleMapper.editRole(role);
    }

    @Override
    public void deleteRole(int roleId) {
        roleMapper.deleteRoleByRoleId(roleId);
        roleMapper.deleteRolePermissionByRoleId(roleId);
    }

    @Override
    @Transactional
    public List<Permission> findOtherPermissions(int roleId) {
        List<Permission> permissionList = roleMapper.findOtherPermissions(roleId);
        return permissionList;
    }

    @Override
    @Transactional
    public void addPermissionsToRole(int roleId, List<Integer> permissionIds) {
        for (Integer permissionId : permissionIds) {
            roleMapper.addPermissionsToRole(roleId,permissionId);
        }
    }

    @Override
    @Transactional
    public void deletePermissionByPermissionId(int roleId, List<Integer> permissionIds) {
        for (Integer permissionId : permissionIds) {
            roleMapper.deletePermissionByPermissionId(roleId,permissionId);
        }
    }
}
