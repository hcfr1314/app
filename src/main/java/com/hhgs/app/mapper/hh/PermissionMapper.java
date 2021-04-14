package com.hhgs.app.mapper.hh;

import com.hhgs.app.model.DO.user.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {


    /**
     * 查询所有权限
     * @return
     */
    List<Permission> findAllPermission();

    /**
     * 保存权限
     * @param permission
     */
    void savePermission(@Param("permission") Permission permission);


    /**
     * 通过permissionId删除权限
     * @param permissionId
     */
    void deleteByPermissionId(@Param("permissionId") int permissionId);

    /**
     *
     * @param permission
     */
    void editPermission(@Param("permission") Permission permission);
}
