package com.hhgs.app.controller.alarm;

import java.util.Map;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.common.Pages;
import com.hhgs.app.model.DO.alarm.AnalyzeAndStatistic;
import com.hhgs.app.service.AlarmAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/analyze")
@Api("告警分析页面接口")
public class AnalyzeController {

    @Autowired
    private AlarmAnalyzeService analyzeService;

    @PostMapping("/barByMonth")
    @ApiOperation("按照月份展示折线图和展示接口")
    public BaseResponse barByMonth(@RequestBody AnalyzeAndStatistic entity) {
        try {
            Map<String, Object> result = analyzeService.barByMonth(entity);
            return BaseResponse.initSuccessBaseResponse(result);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.initError(null, e.getMessage());
        }

    }

}
