package com.team5.campscore.schedules;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.team5.campscore.tools.WeatherTools;

@Component
public class WeatherSchedule {
	@Autowired
	private WeatherTools wTools;

	@Scheduled(cron = "30 36 6 * * *")
	public void autoUpdateScheduleAt6() {
		Map<String,Integer> isSuccess;
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		String tmfc =sdf.format(date)+"0600";
		
		isSuccess = new HashMap<String,Integer>();
		Map<String,String> rcodeMap =new HashMap<String,String>();
		
		int i=0;
		isSuccess.put("item"+ i, 0);
		rcodeMap.put("item"+(i++),"11B20503");//경기11B
		isSuccess.put("item"+ i, 0);
		rcodeMap.put("item"+(i++),"11C10303");//충북11C1
		isSuccess.put("item"+ i, 0);
		rcodeMap.put("item"+(i++),"11C20303");//충남11C2
		isSuccess.put("item"+ i, 0);
		rcodeMap.put("item"+(i++),"11D10302");//강원11D
		isSuccess.put("item"+ i, 0);
		rcodeMap.put("item"+(i++),"11H10502");//경북11H1
		isSuccess.put("item"+ i, 0);
		rcodeMap.put("item"+(i++),"11H20603");//경남11H2
		isSuccess.put("item"+ i, 0);
		rcodeMap.put("item"+(i++),"11F10201");//전북11F1
		isSuccess.put("item"+ i, 0);
		rcodeMap.put("item"+(i++),"11F20503");//전남11F2
		isSuccess.put("item"+ i, 0);
		rcodeMap.put("item"+(i++),"11G00201");//제주11G
		 
		
		for(int j=1;j<3; j++) {// 재시도 횟수
	    	for ( String key : rcodeMap.keySet() ) {
	    		
	    		try {
	    			if(isSuccess.get(key)==0) { //성공하지 못한것만 시도
		    			isSuccess.put(key, wTools.updateWeather(rcodeMap.get(key),tmfc));
		    		}
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		}
	}
	
	
	
	@Scheduled(cron = "0 0 0 * * *")
	public void autoUpdateScheduleAt0() {	
		wTools.updateWeatherMoveUpDate();
	}
	
}
