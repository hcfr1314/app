package com.hhgs.app.controller;


import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.PlantInfo;
import com.hhgs.app.service.PlantInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plant")
@Api("场站相关接口")
public class PlantInfoController {

    @Autowired
    private PlantInfoService plantInfoService;


    @GetMapping("/list")
    @ApiOperation("根据场站类型查询场站基本信息")
    public BaseResponse list(@ApiParam(required = true,value = "场站类型：1 光伏，2风电") @RequestParam("plantType") int plantType,
                             Authentication authentication){
        try {
            List<PlantInfo> list = plantInfoService.getPlantList(plantType,authentication);
            return BaseResponse.initSuccessBaseResponse(list);
        }catch (Exception e){
            return BaseResponse.initError(null,e.getMessage());
        }
    }
}
