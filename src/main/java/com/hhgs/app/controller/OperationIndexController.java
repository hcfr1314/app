package com.hhgs.app.controller;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.PowerIndicator;
import com.hhgs.app.model.VO.OperationIndex;
import com.hhgs.app.service.OperationIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api("运行指标")
@RestController
@RequestMapping("/operationIndex")
@CrossOrigin
public class OperationIndexController {

    private static Logger logger = LoggerFactory.getLogger(OperationIndexController.class);

    @Autowired
    OperationIndexService operationIndexService;

    @ApiOperation(notes = "查询相关指标", value = "查询相关指标")
    @GetMapping("getIndexValue")
    public BaseResponse getIndexValue(@ApiParam(required = true, value = "plantCode") @RequestParam int plantCode,
                                      @ApiParam(required = true, value = "场站类型必传，1，光伏，2，风电") @RequestParam int plantType) {
        try {
            OperationIndex operationIndex = operationIndexService.getDateByTypeAndCode(plantCode, plantType, null);
            return BaseResponse.initSuccessBaseResponse(operationIndex);
        } catch (Exception e) {
            return BaseResponse.initError(null, e.getMessage());
        }
    }

}
