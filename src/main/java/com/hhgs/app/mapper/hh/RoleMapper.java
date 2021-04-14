package com.hhgs.app.mapper.hh;


import com.hhgs.app.model.DO.user.Permission;
import com.hhgs.app.model.DO.user.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {


    /**
     * 通过角色名称查询该角色信息
     * @param roleName
     * @return
     */
    List<Role> loadRoleByRoleName(@Param("roleName") String roleName);

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAllRole();

    /**
     * 保存角色
     * @param role
     */
    void saveRole(@Param("role") Role role);

    /**
     * 编辑角色
     * @param role
     */
    void editRole(@Param("role") Role role);


    /**
     * 查询不属于该角色的其他权限
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermissions(@Param("roleId") int roleId);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     */
    void addPermissionsToRole(@Param("roleId") int roleId,@Param("permissionId") int permissionId);

    /**
     * 删除该角色的权限
     * @param roleId
     * @param permissionId
     */
    void deletePermissionByPermissionId(@Param("roleId") int roleId,@Param("permissionId") int permissionId);

    /**
     * 根据角色id删除角色表数据
     * @param roleId
     */
    void deleteRoleByRoleId(@Param("roleId") int roleId);

    /**
     * 根据角色id删除角色权限中间表的数据
     * @param roleId
     */
    void deleteRolePermissionByRoleId(@Param("roleId") int roleId);
}
