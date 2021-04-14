package com.hhgs.app.controller;

import com.github.pagehelper.PageInfo;
import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.common.Pages;
import com.hhgs.app.model.DO.user.Permission;
import com.hhgs.app.model.DO.user.Role;
import com.hhgs.app.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("角色管理")
@RequestMapping("/role")
@RestController
@CrossOrigin
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @ApiOperation(notes = "查询所有角色",value = "查询所有角色")
    @GetMapping("findAllRole")
    public BaseResponse findAllRole(@ApiParam(required = true, value = "pageSize") @RequestParam(required = true, defaultValue = "10") int pageSize,
                                    @ApiParam(required = true, value = "pageNum") @RequestParam(required = true, defaultValue = "1") int pageNum) {
        try {
            PageInfo<Role> rolePageInfo = roleService.findAllRole(pageSize,pageNum);
            if (rolePageInfo.getList() == null) {
                return BaseResponse.initError(null, "查寻结果为空");
            } else {
                Pages pages = new Pages();
                pages.setPageCount(rolePageInfo.getPages());
                pages.setDataCount((int) rolePageInfo.getTotal());
                pages.setPageNum(rolePageInfo.getPageNum());
                pages.setPageSize(rolePageInfo.getPageSize());
                pages.setDataList(rolePageInfo.getList());
                return BaseResponse.initSuccessBaseResponse(pages);
            }
        } catch (Exception e) {
            logger.error("UserController--findAllRole--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "通过角色名称查询该角色信息",value = "通过角色名称查询该角色信息")
    @GetMapping("loadRoleByRoleName")
    public BaseResponse loadRoleByRoleName(@ApiParam(required = true,value = "roleName") @RequestParam(required = true) String roleName) {
        try {
            List<Role> roleList = roleService.loadRoleByRoleName(roleName);
            return BaseResponse.initSuccessBaseResponse(roleList);
        } catch (Exception e) {
            logger.error("UserController--loadRoleByRoleName--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "保存角色",value = "保存角色")
    @PostMapping("saveRole")
    public BaseResponse saveRole(@ApiParam(required = true,value = "role") @RequestBody(required = true) Role role) {
        try {
            roleService.saveRole(role);
            return BaseResponse.initSuccessBaseResponse("保存角色成功");
        } catch (Exception e) {
            logger.error("UserController--saveRole--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "编辑角色",value = "编辑角色")
    @PostMapping("editRole")
    public BaseResponse editRole(@ApiParam(required = true,value = "role") @RequestBody(required = true) Role role) {
        try {
            roleService.editRole(role);
            return BaseResponse.initSuccessBaseResponse("编辑角色成功");
        } catch (Exception e) {
            logger.error("UserController--editRole--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "删除角色",value = "删除角色")
    @PostMapping("deleteRole")
    public BaseResponse deleteRole(@ApiParam(required = true,value = "roleId") @RequestParam(required = true) int roleId) {
        try {
            roleService.deleteRole(roleId);
            return BaseResponse.initSuccessBaseResponse("删除角色成功");
        } catch (Exception e) {
            logger.error("UserController--deleteRole--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "查询不属于该角色的其他权限",value = "查询不属于该角色的其他权限")
    @GetMapping("findOtherPermissions")
    public BaseResponse findOtherPermissions(@ApiParam(required = true,value = "roleId") @RequestParam(required = true) int roleId) {
        try {
            List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);
            return BaseResponse.initSuccessBaseResponse(otherPermissions);
        } catch (Exception e) {
            logger.error("UserController--findOtherPermissions--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "给角色添加权限",value = "给角色添加权限")
    @PostMapping("addPermissionsToRole")
    public BaseResponse addPermissionsToRole(@ApiParam(required = true,value = "roleId") @RequestParam(required = true) int roleId,
                                             @ApiParam(required = true,value = "permissionIds") @RequestBody(required = true) List<Integer> permissionIds
                                                                                                                                   ) {
        try {
            roleService.addPermissionsToRole(roleId,permissionIds);
            return BaseResponse.initSuccessBaseResponse("给角色添加权限成功");
        } catch (Exception e) {
            logger.error("UserController--addPermissionsToRole--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "删除该角色所拥有的权限",value = "删除该角色所拥有的权限")
    @PostMapping("deletePermissionByPermissionId")
    public BaseResponse deletePermissionByPermissionId(@ApiParam(required = true,value = "roleId") @RequestParam(required = true) int roleId,
                                             @ApiParam(required = true,value = "permissionIds") @RequestBody(required = true) List<Integer> permissionIds
    ) {
        try {
            roleService.deletePermissionByPermissionId(roleId,permissionIds);
            return BaseResponse.initSuccessBaseResponse("删除角色权限成功");
        } catch (Exception e) {
            logger.error("UserController--deletePermissionByPermissionId--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }
}
