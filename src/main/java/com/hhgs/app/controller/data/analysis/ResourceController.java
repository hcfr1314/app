package com.hhgs.app.controller.data.analysis;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.EquipmentRunning;
import com.hhgs.app.model.DO.IrradiationGeneration;
import com.hhgs.app.model.DO.hbase.HbaseResult;
import com.hhgs.app.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resource")
@CrossOrigin
@Api("资源指标相关接口")
public class ResourceController {

    Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    ResourceService resourceService;

    @ApiOperation(notes = "查询辐照度", value = "查询辐照度")
    @GetMapping("queryIrradiance")
    public BaseResponse queryIrradiance(@ApiParam(required = true, value = "plantCode") @RequestParam int plantCode,
                                                      @ApiParam(required = true, value = "currentDate") @RequestParam String currentDate) {
        try {
            Map<String, HbaseResult> resultMap = resourceService.queryIrradiance(plantCode, currentDate);
            return BaseResponse.initSuccessBaseResponse(resultMap);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }


    @ApiOperation(notes = "查询辐照量和日照小时数", value = "查询辐照量和日照小时数")
    @GetMapping("queryIrradiationAndSunHours")
    public BaseResponse queryIrradiation(@ApiParam(required = true, value = "plantCode") @RequestParam int plantCode,
                                         @ApiParam(required = true, value = "currentMonth") @RequestParam String currentMonth) {
        try {
            List<IrradiationGeneration> irradiationGenerations = resourceService.queryIrradiationAndSunHours(plantCode, currentMonth);
            return BaseResponse.initSuccessBaseResponse(irradiationGenerations);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }
}
