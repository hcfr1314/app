package com.hhgs.app.util;

import com.alibaba.fastjson.JSONObject;
import com.hhgs.app.model.DO.hbase.HbaseResult;
import com.hhgs.app.model.DO.hbase.LoginCheckResult;
import com.hhgs.app.model.DO.hbase.QueryObjectData;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

public class SelectDataFromInfluxdb {

    private static Logger logger = LoggerFactory.getLogger(SelectDataFromInfluxdb.class);

    public static Map<String, String[]> getData(List<String> ids, String ipddr, String dbName) {


        InfluxDBConnection influxDBConnection = new InfluxDBConnection("admin", "admin", ipddr, dbName, "hour");

        //拼接sql语句
        StringBuilder sb1 = new StringBuilder();
        sb1.append("SELECT * FROM data where (");
        //sb1.append("SELECT * FROM chaka where (");
        for (String id : ids) {
            sb1.append(" id = '" + id + "' or ");
        }
        int index = sb1.lastIndexOf("or");
        sb1.delete(index, sb1.length());
        sb1.append(") ");
        long curremtTime = DateUtil.getCurrentTime();
        long freTime = curremtTime - 180000;
        sb1.append("and time >= " + freTime + "ms " + "and time <= " + curremtTime + "ms" + " order by time asc limit 1 tz('Asia/Shanghai')");

        Map<String, String[]> resultMap = null;

        try {
            QueryResult results = influxDBConnection.query(sb1.toString());
            //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
            QueryResult.Result oneResult = results.getResults().get(0);
            if (oneResult.getSeries() != null) {
                List<List<Object>> valueList = oneResult.getSeries().stream().map(QueryResult.Series::getValues)
                        .collect(Collectors.toList()).get(0);
                resultMap = new HashMap<>();
                if (valueList != null && valueList.size() > 0) {
                    for (List<Object> value : valueList) {
                        //  数据库中字段1取值(时间戳)
                        String field1 = value.get(0) == null ? null : value.get(0).toString();
                        //  数据库中字段2取值(原始测点)
                        String field2 = value.get(1) == null ? null : value.get(1).toString();
                        //  数据质量
                        String field3 = value.get(2) == null ? null : value.get(2).toString();
                        //  实时值
                        String field4 = value.get(3) == null ? null : value.get(3).toString();

                        //将查询结果放入数组
                        String[] arr = {field2, field3, field4, field1};
                        //
                        resultMap.put(field2, arr);

                        // TODO 用取出的字段做你自己的业务逻辑……
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            influxDBConnection.close();
        }

        return resultMap;
    }


    public static Map<String, String[]> getData(String id , Date startTime, Date endTime, String ipddr, String dbName) {


        InfluxDBConnection influxDBConnection = new InfluxDBConnection("admin", "admin", ipddr, dbName, "hour");

        //拼接sql语句
        StringBuilder sb1 = new StringBuilder();
        sb1.append("SELECT * FROM data where ");
        //sb1.append("SELECT * FROM chaka where (");
        sb1.append(" id = " +" '"+id+"'" );

        long startTimes = startTime.getTime();
        long endTimes = endTime.getTime();
        sb1.append(" and time >= " + startTimes + "ms " + "and time <= " + endTimes + "ms" + " order by time asc tz('Asia/Shanghai')");

        System.out.println(sb1.toString());

        Map<String, String[]> resultMap = null;

        try {
            QueryResult results = influxDBConnection.query(sb1.toString());
            //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
            QueryResult.Result oneResult = results.getResults().get(0);
            if (oneResult.getSeries() != null) {
                List<List<Object>> valueList = oneResult.getSeries().stream().map(QueryResult.Series::getValues)
                        .collect(Collectors.toList()).get(0);
                resultMap = new HashMap<>();
                if (valueList != null && valueList.size() > 0) {
                    for (List<Object> value : valueList) {
                        //  数据库中字段1取值(时间戳)
                        String field1 = value.get(0) == null ? null : value.get(0).toString();
                        //  数据库中字段2取值(原始测点)
                        String field2 = value.get(1) == null ? null : value.get(1).toString();
                        //  数据质量
                        String field3 = value.get(2) == null ? null : value.get(2).toString();
                        //  实时值
                        String field4 = value.get(3) == null ? null : value.get(3).toString();

                        //将查询结果放入数组
                        String[] arr = {field2, field4};
                        //
                        resultMap.put(field1, arr);

                        // TODO 用取出的字段做你自己的业务逻辑……
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            influxDBConnection.close();
        }

        return resultMap;
    }


    private static final String URL = "http://172.28.8.25/avatar-datamanage-service/data/queryMetaObjData?metaObjectName=";

    private static final String SUCCESS_CODE = "0";

    public static HbaseResult getDataFromInfluxdb(String dataObjectName, String startTime, String endTime, long id) {

        String p = "{'starttime': '" + startTime + "ms','id':'" + id + "','fillPoint':'" + 0 + "','sampled':'" + 3600 + "','endtime': '" + endTime + "ms'}";

        QueryObjectData param = new QueryObjectData(p, null);

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
        String json = HttpUtils.getInstance().doPostByToken(URL, token, dataObjectName, param);

        HbaseResult hbaseResult = JSONObject.parseObject(json, HbaseResult.class);

        if (SUCCESS_CODE.equals(hbaseResult.getStatusCode())) {
            return hbaseResult;
        }

        return null;
    }

    public static void main(String[] args) {
        String ids="313633542376";
        Map<String, String[]> texu = SelectDataFromInfluxdb.getData(Arrays.asList(ids), "http://172.28.8.12:8086", "zhuyu");
        String s = texu.get(ids)[2];
        System.out.println(s);
    }

}
