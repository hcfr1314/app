package com.hhgs.app.controller.data.analysis;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.ActivePowerAndIrradiance;
import com.hhgs.app.model.DO.EquipmentRunning;
import com.hhgs.app.model.DO.IrradiationGeneration;
import com.hhgs.app.model.DO.hbase.HbaseResult;
import com.hhgs.app.model.VO.OperationIndex;
import com.hhgs.app.service.RuningService;
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
@RequestMapping("/running")
@CrossOrigin
@Api("设备运行指标相关接口")
public class RunningController {


    Logger logger = LoggerFactory.getLogger(RunningController.class);

    @Autowired
    RuningService runingService;

  /*  @ApiOperation(notes = "查询有功功率和辐照度", value = "查询有功功率和辐照度")
    @GetMapping("queryActivePowerAndIrradiance")
    public BaseResponse queryActivePowerAndIrradiance(@ApiParam(required = true, value = "plantCode") @RequestParam int plantCode,
                                      @ApiParam(required = true, value = "currentMonth") @RequestParam String currentMonth) {
        try {
            List<ActivePowerAndIrradiance> powerAndIrradiances = runingService.queryActivePowerAndIrradiance(plantCode, currentMonth);
            return BaseResponse.initSuccessBaseResponse(powerAndIrradiances);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }
*/

   /* @ApiOperation(notes = "查询有功功率和辐照度", value = "查询有功功率和辐照度")
    @GetMapping("queryActivePowerAndIrradiance")
    public BaseResponse queryActivePowerAndIrradiance(@ApiParam(required = true, value = "originalId") @RequestParam String originalId,
                                                      @ApiParam(required = true, value = "currentDate") @RequestParam String currentDate,
                                                      @ApiParam(required = true, value = "ipddr") @RequestParam String ipddr,
                                                      @ApiParam(required = true, value = "dbName") @RequestParam String dbName
                                                      ) {
        try {
            Map<String, String[]> resultMap = runingService.queryActivePowerAndIrradiance(originalId, currentDate,ipddr,dbName);
            return BaseResponse.initSuccessBaseResponse(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }*/

    @ApiOperation(notes = "查询有功功率和辐照度", value = "查询有功功率和辐照度")
    @GetMapping("queryActivePowerAndIrradiance")
    public BaseResponse queryActivePowerAndIrradiance(@ApiParam(required = true, value = "plantCode") @RequestParam int plantCode,
                                                      @ApiParam(required = true, value = "currentDate") @RequestParam String currentDate
    ) {
        try {
            Map<String, HbaseResult> resultMap = runingService.queryActPowerAndIrr(plantCode,currentDate);
            return BaseResponse.initSuccessBaseResponse(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @ApiOperation(notes = "查询辐照量和发电量", value = "查辐辐照量和发电量")
    @GetMapping("queryIrradiationGeneration")
    public BaseResponse queryIrradiationGeneration(@ApiParam(required = true, value = "plantCode") @RequestParam int plantCode,
                                      @ApiParam(required = true, value = "currentMonth") @RequestParam String currentMonth) {
        try {
            List<IrradiationGeneration> irradiationGenerations = runingService.queryIrradiationGeneration(plantCode, currentMonth);
            return BaseResponse.initSuccessBaseResponse(irradiationGenerations);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }


}
