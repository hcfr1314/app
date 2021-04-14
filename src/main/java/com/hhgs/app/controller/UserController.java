package com.hhgs.app.controller;

import com.github.pagehelper.PageInfo;
import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.common.Pages;
import com.hhgs.app.model.DO.user.Group;
import com.hhgs.app.model.DO.user.Role;
import com.hhgs.app.model.DO.user.User;
import com.hhgs.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("用户管理")
@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ApiOperation(notes = "查询所有用户", value = "查询所有用户")
    @GetMapping("queryAllUser")
    public BaseResponse queryAllUser(@ApiParam(required = true, value = "pageSize") @RequestParam(required = true, defaultValue = "10") int pageSize,
                                     @ApiParam(required = true, value = "pageNum") @RequestParam(required = true, defaultValue = "1") int pageNum) {
        try {
            PageInfo<User> userPageInfo = userService.findAllUser(pageSize,pageNum);
            if (userPageInfo.getList() == null) {
                return BaseResponse.initError(null, "查寻结果为空");
            } else {
                Pages pages = new Pages();
                pages.setPageCount(userPageInfo.getPages());
                pages.setDataCount((int) userPageInfo.getTotal());
                pages.setPageNum(userPageInfo.getPageNum());
                pages.setPageSize(userPageInfo.getPageSize());
                pages.setDataList(userPageInfo.getList());
                return BaseResponse.initSuccessBaseResponse(pages);
            }
        } catch (Exception e) {
            logger.error("UserController--queryAllUser--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation(notes = "查询该用户以及该用户所拥有的角色",value = "查询该用户以及该用户所拥有的角色")
    @GetMapping("queryUserByUsername")
    public BaseResponse queryUserByUsername(@ApiParam(required = true,value = "username") @RequestParam(required = true) String username) {
        try {
            User user = userService.queryUserByUsername(username);
            return BaseResponse.initSuccessBaseResponse(user);
        } catch (Exception e) {
            logger.error("UserController--queryUserByUsername--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "保存用户信息",value = "保存用户信息")
    @PostMapping("saveUser")
    public BaseResponse saveUser(@ApiParam(required = true,value = "user") @RequestBody(required = true) User user) {
        try {
            userService.saveUser(user);
            return BaseResponse.initSuccessBaseResponse("保存成功");
        } catch (Exception e) {
            logger.error("UserController--saveUser--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "编辑用户",value = "编辑用户")
    @PostMapping("editUser")
    public BaseResponse editUser(@ApiParam(required = true,value = "user") @RequestBody(required = true) User user) {
        try {
            userService.editUser(user);
            return BaseResponse.initSuccessBaseResponse("编辑成功");
        } catch (Exception e) {
            logger.error("UserController--editUser--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }


    @ApiOperation(notes = "校验原始密码,返回值1:密码校验通过/0:密码校验不通过",value = "校验原始密码")
    @PostMapping("checkPassword")
    public BaseResponse checkPassword(@ApiParam(required = true,value = "user") @RequestBody(required = true) User user,
                                     @ApiParam(required = true,value = "password") @RequestParam(required = true) String originalPassword) {
        try {
            int repCode = userService.checkPassword(user, originalPassword);
            if (repCode == 1) {
                return BaseResponse.initSuccessBaseResponse(repCode,"原始密码正确");
            }else {
                return BaseResponse.initError(repCode,"原始密码错误");
            }

        } catch (Exception e) {
            logger.error("UserController--editPassword--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }


    @ApiOperation(notes = "删除用户",value = "删除用户")
    @PostMapping("deleteUser")
    public BaseResponse deleteUser(@ApiParam(required = true,value = "user")@RequestParam(required = true) int userId) {
        try {
            userService.deleteUser(userId);
            return BaseResponse.initSuccessBaseResponse("删除成功");
        } catch (Exception e) {
            logger.error("UserController--deleteUser--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "查找不属于该用户的其他角色",value = "查找不属于该用户的其他角色")
    @GetMapping("findOtherRoles")
    public BaseResponse findOtherRoles(@ApiParam(required = true,value = "userId")@RequestParam(required = true) int userId) {
        try {
            List<Role> otherRoles = userService.findOtherRoles(userId);
            return BaseResponse.initSuccessBaseResponse(otherRoles);
        } catch (Exception e) {
            logger.error("UserController--findOtherRoles--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }


    @ApiOperation(notes = "给用户添加角色",value = "给用户添加角色")
    @PostMapping("addRolesToUser")
    public BaseResponse addRolesToUser(@ApiParam(required = true,value = "userId") @RequestParam(required = true) int userId,
                                       @ApiParam(required = true,value = "roleIds") @RequestBody(required = true) List<Integer> roleIds) {
        try {
            userService.addRolesToUser(userId,roleIds);
            return BaseResponse.initSuccessBaseResponse("添加角色成功");
        } catch (Exception e) {
            logger.error("UserController--addRolesToUser--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(notes = "删除当前用户拥有角色",value = "删除当前用户拥有角色")
    @PostMapping("deleteRole")
    public BaseResponse deleteRole(@ApiParam(required = true,value = "userId") @RequestParam(required = true) int userId,
                                   @ApiParam(required = true,value = "") @RequestBody(required = true) List<Integer> roleIds) {
        try {
            userService.deleteRole(userId,roleIds);
            return BaseResponse.initSuccessBaseResponse("删除角色成功");
        } catch (Exception e) {
            logger.error("UserController--deleteRole--e" + e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }
}
