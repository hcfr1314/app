package com.hhgs.app.controller.data.analysis;

import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.StandardValue;
import com.hhgs.app.service.StandardValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/update")
@CrossOrigin
@Api("更新综合厂用电率和光伏电站可利用率标准值相关接口")
public class StandardValueController {

    @Autowired
    private StandardValueService standardValueService;

    @ApiOperation("更新接口")
    public BaseResponse update(@ApiParam(value = "场站编号",required = true) @RequestParam int plantCode,
                               @ApiParam(value = "综合厂用电率值",required = true) @RequestParam double val,
                               @ApiParam(value = "综合厂用电率值",required = true) @RequestParam double val2){
        try{
            int i=standardValueService.updateStandardValue(plantCode,val,val2);
            return BaseResponse.initSuccessBaseResponse(i);
        }catch (Exception e){
            return BaseResponse.initError(null,e.getMessage());
        }

    }

    @ApiOperation("查看当前用户下的所有的参考值")
    @GetMapping("/list")
    public BaseResponse list(){
        try{
            List<StandardValue> list=standardValueService.list();
            return BaseResponse.initSuccessBaseResponse(list);
        }catch (Exception e){
            return BaseResponse.initError(null,e.getMessage());
        }
    }
}
