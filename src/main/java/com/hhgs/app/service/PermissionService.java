package com.hhgs.app.service;

import com.github.pagehelper.PageInfo;
import com.hhgs.app.model.DO.user.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * 查询所有权限
     * @return
     */
    PageInfo<Permission> findAllPermission(int pageSize, int pageNum);

    /**
     * 保存权限
     * @param permission
     */
    void savePermission(Permission permission);


    /**
     * 通过permissionId删除权限
     * @param permissionId
     */
    void deleteByPermissionId(int permissionId);

    /**
     * 编辑权限
     * @param permission
     */
    void editPermission(Permission permission);
}
