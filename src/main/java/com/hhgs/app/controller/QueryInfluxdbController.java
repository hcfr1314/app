package com.hhgs.app.controller;


import com.hhgs.app.model.DO.hbase.QueryObjectData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Api("时序数据查询")
@RestController
@RequestMapping("/queryInfluxdb")
@CrossOrigin
public class QueryInfluxdbController {

    private static Logger logger = LoggerFactory.getLogger(QueryInfluxdbController.class);

    @ApiOperation( notes = "查询时序数据",value = "查询时序数据")
    @PostMapping("querydata")
    @CrossOrigin
    public Object querydata(@ApiParam(required = true, value = "token") @RequestParam(required = true) String token,
                            @ApiParam(required = true, value = "metaObjectName") @RequestParam(required = true) String metaObjectName,
                            @ApiParam(required = true, value = "queryObjectData") @RequestBody(required = true) QueryObjectData queryObjectData) {

//        String url = "http://172.28.8.100/avatar/data/queryMetaObjData?metaObjectName=";
//        try {
//            String result = HttpUtil.doPost(url,token, metaObjectName, queryObjectData);
//            Object parse = JSON.parse(result);
//            return parse;
//        } catch (Exception e) {
//            logger.error("QueryInfluxdbController--querydata--e"+e.getMessage());
//            e.printStackTrace();
//            return BaseResponse.initError(null,e.getMessage());
//        }
        return null;
    }


}
