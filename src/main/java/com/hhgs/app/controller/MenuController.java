package com.hhgs.app.controller;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.user.Menu;
import com.hhgs.app.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin
@Api("菜单相关接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("获取当前用户的菜单")
    @GetMapping("/list")
    public BaseResponse menuList(Authentication user) {
        try {
            List<Menu> list = menuService.menuList(user);
            return BaseResponse.initSuccessBaseResponse(list);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }
}
