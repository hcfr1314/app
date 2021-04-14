package com.hhgs.app.controller.data.analysis;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.alarm.AlarmAnalyze;
import com.hhgs.app.model.VO.IndexPlanRate;
import com.hhgs.app.service.AlarmAnalyzeService;
import com.hhgs.app.service.PowerIndicatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/index")
@CrossOrigin
@Api("首页指标相关接口")
public class IndexController {

    @Autowired
    private AlarmAnalyzeService alarmAnalyzeService;

    @Autowired
    private PowerIndicatorService powerIndicatorService;

    @GetMapping("/day")
    @ApiOperation("昨日告警发生次数")
    public BaseResponse dailyStat(@ApiParam(value = "场站名称",required = true) @RequestParam String plantName){
        try{
            List<AlarmAnalyze> result=alarmAnalyzeService.yesterdayData(plantName);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @GetMapping("/recentWeek")
    @ApiOperation("进一周每天的次数")
    public BaseResponse recentWeek(@ApiParam(value = "场站名称",required = true) @RequestParam String plantName){
        try{
            List<AlarmAnalyze> result=alarmAnalyzeService.recentWeek(plantName);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @GetMapping("/planRate")
    @ApiOperation("月计划发电量完成率")
    public BaseResponse planRate(@ApiParam(value = "场站编号",required = true) @RequestParam int plantCode){
        try{
            IndexPlanRate result=powerIndicatorService.planRate(plantCode);
            return BaseResponse.initSuccessBaseResponse(result);
        }catch (Exception e){
            return BaseResponse.initError(null,e.getMessage());
        }
    }

}
