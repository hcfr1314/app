package com.hhgs.app.util;

import com.alibaba.fastjson.JSONObject;
import com.hhgs.app.model.DO.hbase.HbasePoint;
import com.hhgs.app.model.DO.hbase.HbaseResult;
import com.hhgs.app.model.DO.hbase.LoginCheckResult;
import com.hhgs.app.model.DO.hbase.QueryObjectData;

import java.util.List;

public class HbaseUtils {

    private static final String HBASE_URL = "http://172.28.8.25/avatar-datamanage-service/data/queryMetaObjData?metaObjectName=";

    private static final String SUCCESS_CODE = "0";

    /**
     * 从hbase获取数据
     *
     * @param dataObjectName
     * @param startTime
     * @param endTime
     * @param ids
     * @return
     */
    public static HbaseResult getDataFromHbase(String dataObjectName, String startTime, String endTime, List<HbasePoint> ids) {

        String p = "{'starttime': '" + startTime + "','endtime': '" + endTime + "'}";
        String filterStr = idsToString(ids);

        if (filterStr == null) {
            return null;
        }

        QueryObjectData param = new QueryObjectData(p, filterStr.toString());

        //调用阿凡达接口，首先需要获取token
        LoginCheckResult result = null;
        try {
            result = LoginCheckUtils.LoginCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!SUCCESS_CODE.equals(result.getStatusCode())) {
            return null;
        }

        String token = result.getData();

        //调用阿凡达接口
        String json = HttpUtils.getInstance().doPostByToken(HBASE_URL, token, dataObjectName, param);

        HbaseResult hbaseResult = JSONObject.parseObject(json, HbaseResult.class);

        if (SUCCESS_CODE.equals(hbaseResult.getStatusCode())) {
            return hbaseResult;
        }

        return null;
    }

    /**
     * 拼接id查询条件
     *
     * @param ids
     * @return
     */
    private static String idsToString(List<HbasePoint> ids) {
        StringBuilder result = new StringBuilder("{");
        if (ids == null || ids.isEmpty()) {
            return result.append("}").toString();
        }

        for (int i = 0; i < ids.size(); i++) {
            String id = String.valueOf(ids.get(i).getId());
            if (i == ids.size() - 1) {
                result.append("'ID'" + ":" + "'" + id + "'");
            } else {
                result.append("'ID'" + ":" + "'" + id + "',");
            }
        }
        ;
        result.append("}");
        return result.toString();
    }
}
