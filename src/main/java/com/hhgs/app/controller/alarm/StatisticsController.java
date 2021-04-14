package com.hhgs.app.controller.alarm;

import com.github.pagehelper.PageInfo;
import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.alarm.AlarmStatistics;
import com.hhgs.app.model.DO.alarm.AnalyzeAndStatistic;
import com.hhgs.app.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin
@RequestMapping("/statistics")
@Api("统计页面接口")
public class StatisticsController {
 
	@Autowired
	private StatisticsService statService;
	
	@PostMapping("/list")
	@ApiOperation("多条件查询接口")
	public BaseResponse list(@ApiParam(required = true,value="entity") @RequestBody  AnalyzeAndStatistic entity,
							 @ApiParam(required = true, value = "pageSize", defaultValue = "10") @RequestParam int pageSize,
							 @ApiParam(required = true, value = "pageNumber", defaultValue = "1") @RequestParam int pageNumber) {
		try {
			PageInfo<AlarmStatistics> result=statService.getForPage(entity,pageSize,pageNumber);
			return BaseResponse.initSuccessBaseResponse(result);
		}catch(Exception e) {
			e.printStackTrace();
			return BaseResponse.initError(null, e.getMessage());
		}
		
	}
}
