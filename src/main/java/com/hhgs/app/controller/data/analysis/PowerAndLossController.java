package com.hhgs.app.controller.data.analysis;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.EnergyComsume;
import com.hhgs.app.service.EnergyComsumeService;
import com.hhgs.app.service.PowerIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/powerloss")
@CrossOrigin
@Api("电量及损耗指标相关接口")
public class PowerAndLossController {

    @Autowired
    private EnergyComsumeService energyComsumeService;

    @Autowired
    private PowerIndicatorService powerIndicatorService;

    @GetMapping("/rate")
    @ApiOperation("综合厂用电率")
    public BaseResponse rate(@ApiParam(value = "场站编号", required = true) @RequestParam int plantCode,
                             @ApiParam(value = "查询日期（格式2020-08）", required = true) @RequestParam String date) {
        try {
            Map<String,Object> result=energyComsumeService.monthOfRate(plantCode,date);
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @GetMapping("/powerOfMonth")
    @ApiOperation("电站月发电量")
    public BaseResponse powerOfMonth(@ApiParam(value = "场站编号", required = true) @RequestParam int plantCode,
                             @ApiParam(value = "查询日期（格式2020-08）", required = true) @RequestParam String date) {
        try {
            Map<String, List<Double>> result=powerIndicatorService.powerOfMonth(plantCode,date);
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }


}
