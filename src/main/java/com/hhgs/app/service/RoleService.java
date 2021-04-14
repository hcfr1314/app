package com.hhgs.app.service;

import com.github.pagehelper.PageInfo;
import com.hhgs.app.model.DO.user.Permission;
import com.hhgs.app.model.DO.user.Role;

import java.util.List;

public interface RoleService {

    /**
     * 通过角色名称查询该角色信息
     * @param roleName
     * @return
     */
    List<Role> loadRoleByRoleName (String roleName);

    /**
     * 查询所有角色
     * @return
     */
    PageInfo<Role> findAllRole(int pageSize, int pageNum);

    /**
     * 保存角色
     * @param role
     */
    void saveRole(Role role);

    /**
     * 编辑角色
     * @param role
     */
    void editRole(Role role);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRole(int roleId);


    /**
     * 查询不属于该角色的其他权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermissions(int roleId);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionsToRole(int roleId,List<Integer> permissionIds);

    /**
     * 删除该角色所拥有的权限
     * @param roleId
     * @param permissionIds
     */
    void deletePermissionByPermissionId( int roleId,List<Integer> permissionIds);
}
