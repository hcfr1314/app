package com.hhgs.app.controller.alarm;

import java.util.List;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.alarm.AlarmHistory;
import com.hhgs.app.service.AlarmHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin
@RequestMapping("/histroy")
@Api(value = "告警历史相关接口")
public class HistoryController {

	@Autowired
	private AlarmHistoryService alarmHistoryService;

	@PostMapping("/list")
	@ApiOperation(notes = "根据条件查询历史列表", value = "根据条件查询历史列表")
	public BaseResponse getList(@ApiParam(required = true, value = "histroy") @RequestBody AlarmHistory histroy,
			@ApiParam(required = true, value = "pageSize", defaultValue = "10") @RequestParam int pageSize,
			@ApiParam(required = true, value = "pageNumber", defaultValue = "1") @RequestParam int pageNumber) {
		try {
			PageInfo<AlarmHistory> result = alarmHistoryService.getPageList(histroy, pageSize, pageNumber);
			return BaseResponse.initSuccessBaseResponse(result);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResponse.initError(null, e.getMessage());
		}
	}

	@PostMapping("/update")
	@ApiOperation("更新手机app未读已读状态")
	public BaseResponse updateStatus(@ApiParam(required = true, value = "需要修改状态的list集合") @RequestBody List<Integer> ids,
									 @ApiParam(required = true, value = "场站名称必传") @RequestParam String plantName) {
		try {
			int i = alarmHistoryService.updateStatus(ids, plantName);
			return BaseResponse.initSuccessBaseResponse("成功更新了" + i + "条记录");
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResponse.initError(null, e.getMessage());
		}
	}

}
