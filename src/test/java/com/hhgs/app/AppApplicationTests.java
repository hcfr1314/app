package com.hhgs.app;


import com.hhgs.app.mapper.hh.InfluxDBDataObjectMapper;
import com.hhgs.app.model.DO.InfluxDBDataObject;
import com.hhgs.app.model.DO.Points;
import com.hhgs.app.model.DO.hbase.HbaseObject;
import com.hhgs.app.model.DO.hbase.HbasePoint;
import com.hhgs.app.service.HbaseObjectService;
import com.hhgs.app.service.impl.OperationIndexServiceImpl;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AppApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private InfluxDBDataObjectMapper mapper;

	@Autowired
	private OperationIndexServiceImpl implement;

	@Autowired
	private HbaseObjectService hbaseObjectService;


	public static void main(String[] args) {
		List<String> strings = Arrays.asList(
				"1号风机日发电量",
				"2号风机日发电量",
				"3号风机日发电量",
				"4号风机日发电量",
				"5号风机日发电量",
				"6号风机日发电量"
		);

		List<String> str = strings.stream().filter(e -> Integer.parseInt(e.split("号")[0]) >= 2).collect(Collectors.toList());
		str.forEach(System.out::println);
	}

	@Test
	public  void objectNameTest() {
		Map<Integer, List<HbaseObject>> hbaseObejcMap = hbaseObjectService.queryAllHbaseObject();
		hbaseObejcMap.forEach(((plantCode,List)-> {

			System.out.println(plantCode+"-------------"+List.get(0));
		}));
	}

	@Test
	public void influxdb(){
		//List<HbasePoint> hbasePoints = Arrays.asList(new HbasePoint[]{new HbasePoint(4395766129L)});
		Points points=new Points();
		points.setOrgId(4395766129L);
		Object data = implement.getData("光电场.黄河珠玉光伏电站", points);
		System.out.println(data);
	}

}
