package com.hhgs.app.controller.alarm;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.service.PlantMappingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("告警场站信息相关接口")
@RestController
@RequestMapping("/alarm/plant")
@CrossOrigin
public class HistoryPlantInfoController {

    @Autowired
    private PlantMappingService plantMappingService;

    @ApiOperation("告警场站查询")
    @GetMapping("/list")
    public BaseResponse plantList(Authentication authentication) {
        try {
            return BaseResponse.initSuccessBaseResponse(plantMappingService.getAllPlantMapping(authentication));
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }

}
