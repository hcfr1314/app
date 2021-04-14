package com.hhgs.app.controller;

import com.github.pagehelper.PageInfo;
import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.common.Pages;
import com.hhgs.app.model.DO.user.Permission;
import com.hhgs.app.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("权限管理")
@RequestMapping("/permission")
@RestController
@CrossOrigin
public class PermissionController {

    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    PermissionService permissionService;

    @ApiOperation(notes = "查询所有权限",value = "查询所有权限")
    @GetMapping("findAllPermission")
    public BaseResponse findAllPermission(@ApiParam(required = true, value = "pageSize") @RequestParam(required = true, defaultValue = "10") int pageSize,
                                          @ApiParam(required = true, value = "pageNum") @RequestParam(required = true, defaultValue = "1") int pageNum) {
        try {
            PageInfo<Permission> permissionPageInfo = permissionService.findAllPermission(pageSize,pageNum);
            if (permissionPageInfo.getList() == null) {
                return BaseResponse.initError(null, "查寻结果为空");
            } else {
                Pages pages = new Pages();
                pages.setPageCount(permissionPageInfo.getPages());
                pages.setDataCount((int) permissionPageInfo.getTotal());
                pages.setPageNum(permissionPageInfo.getPageNum());
                pages.setPageSize(permissionPageInfo.getPageSize());
                pages.setDataList(permissionPageInfo.getList());
                return BaseResponse.initSuccessBaseResponse(pages);
            }
        } catch (Exception e) {
            logger.error("UserController--findAllPermission--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "保存权限",value = "保存权限")
    @PostMapping("savePermission")
    public BaseResponse savePermission(@ApiParam(required = true,value = "permission") @RequestBody(required = true) Permission permission) {
        try {
            permissionService.savePermission(permission);
            return BaseResponse.initSuccessBaseResponse("保存权限成功");
        } catch (Exception e) {
            logger.error("UserController--savePermission--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "通过permissionId删除权限",value = "通过permissionId删除权限")
    @PostMapping("deleteByPermissionId")
    public BaseResponse deleteByPermissionId(@ApiParam(required = true,value = "permissionId") @RequestParam(required = true) int permissionId) {
        try {
            permissionService.deleteByPermissionId(permissionId);
            return BaseResponse.initSuccessBaseResponse("删除权限成功");
        } catch (Exception e) {
            logger.error("UserController--deleteByPermissionId--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "编辑权限",value = "编辑权限")
    @PostMapping("editPermission")
    public BaseResponse editPermission(@ApiParam(required = true,value = "permission") @RequestBody(required = true) Permission permission) {
        try {
            permissionService.editPermission(permission);
            return BaseResponse.initSuccessBaseResponse("编辑权限成功");
        } catch (Exception e) {
            logger.error("UserController--editPermission--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }
}
