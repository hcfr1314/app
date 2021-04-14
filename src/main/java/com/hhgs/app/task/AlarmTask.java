package com.hhgs.app.task;

import com.alibaba.fastjson.JSON;
import com.hhgs.app.model.DO.alarm.AlarmHistory;
import com.hhgs.app.model.DO.alarm.AlarmPlantMapping;
import com.hhgs.app.server.WebSocketServer;
import com.hhgs.app.service.AlarmHistoryService;
import com.hhgs.app.service.PlantMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@EnableScheduling
public class AlarmTask {

    public static String stationName="珠玉光伏电站";

    @Autowired
    private AlarmHistoryService alarmHistoryService;

    @Autowired
    private PlantMappingService plantMappingService;

    @Scheduled(fixedDelay = 3000)
    public void queryAlarm(){

        AlarmPlantMapping plantInfo = plantMappingService.getByPlantName(stationName);

        String tableName = plantInfo.getHistoryTableName();

        List<AlarmHistory> alarmHistories = alarmHistoryService.topTen(tableName);

        WebSocketServer.sendInfo(JSON.toJSONString(alarmHistories));
    }

}
