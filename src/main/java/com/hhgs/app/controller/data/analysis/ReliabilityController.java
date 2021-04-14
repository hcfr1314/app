package com.hhgs.app.controller.data.analysis;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.PowerIndicator;
import com.hhgs.app.service.ReliabilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reliablity")
@CrossOrigin
@Api("可靠性指标相关接口")
public class ReliabilityController {

    @Autowired
    private ReliabilityService reliabilityService;

    @GetMapping("/rate")
    @ApiOperation("光伏电站可利用率")
    public BaseResponse rate(@ApiParam(value = "场站编号", required = true) @RequestParam int plantCode,
                             @ApiParam(value = "查询日期（格式2020-08）", required = true) @RequestParam String date) {
        try {
            Map<String, Object> result = reliabilityService.rate(plantCode, date);
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }

    @GetMapping("/hour")
    @ApiOperation("利用小时")
    public BaseResponse hour(@ApiParam(value = "场站编号", required = true) @RequestParam int plantCode,
                             @ApiParam(value = "查询日期（格式2020-08）", required = true) @RequestParam String date) {
        try {
            List<PowerIndicator> result = reliabilityService.hour(plantCode, date);
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }
}
