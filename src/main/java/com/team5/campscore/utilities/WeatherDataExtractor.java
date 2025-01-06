package com.team5.campscore.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum WeatherDataIndex{
	REGID, TMFC, TMEF, MOD, STN, C, WD, WCD,  CONF, WC_STR, RP;

	
}

public class WeatherDataExtractor {
	
	public String[] findWCData(String rawData) {
		

        // 정규 표현식을 사용하여 예시 문자열 패턴을 찾습니다.
        Pattern pattern = Pattern.compile("([0-9]{2}[A-Z][0-9]{5})\\s+(\\d{12})\\s+(\\d{12})\\s+(A0[0-9])\\s+(\\d+)\\s+(\\d+)\\s+(WB[0-9]{2})\\s+(WB[0-9]{2})\\s+(.+)\\s+\"(.+?)\"\\s+(\\d+)");
        Matcher matcher = pattern.matcher(rawData);

        // 일치하는 모든 문자열을 저장할 리스트를 생성합니다.
        ArrayList<String> matches = new ArrayList<>();

        // 데이터에서 일치하는 모든 문자열을 찾아 리스트에 추가합니다.
        int loop_cnt=0;
        int size= 11;
        String tmp="";
        while (matcher.find()) {
        	tmp="";
        	for (int i = 1; i <= matcher.groupCount(); i++) {
        	    tmp=tmp+matcher.group(i).replace(" ","")+" ";
        	}
        	matches.add(tmp);
        }

        // 리스트를 배열로 변환합니다.
        String[] forecastArray = matches.toArray(new String[0]);

        // 결과를 출력합니다.
        for (String forecast : forecastArray) {
            System.out.println(forecast);
        }
        
        return forecastArray;

	}
	
	public Map<String,String> splitLineWC(String line){
		Map<String,String> returnVal = new HashMap<String,String>();
		
		String[] entity;
		entity=line.split("\\s+");
		if(entity.length==0) {
			return null;
		}
		
		for (int i=0;i<entity.length;i++) {
			returnVal.put("regId",entity[0]);
			returnVal.put("tmef",entity[2]);
			returnVal.put("wc",entity[6]);
			returnVal.put("wcd",entity[7]);
			returnVal.put("rp",entity[10]);
			
		}
		
	
		
		System.out.println(returnVal.toString());
		
		return returnVal;
	}

	public String[] findTPData(String rawData) {

		
		Pattern pattern = Pattern.compile("[0-9]{2}[A-Z][0-9]{5}\\s+\\d{12}\\s+\\d{12}\\s+A0[0-9]\\s+\\d+\\s+\\d+\\s+[-]{0,1}\\d+\\s+[-]{0,1}\\d+\\s+[-]{0,1}\\d+\\s+[-]{0,1}\\d+\\s+[-]{0,1}\\d+\\s+[-]{0,1}\\d+");
		Matcher matcher = pattern.matcher(rawData);

        // 일치하는 모든 문자열을 저장할 리스트를 생성합니다.
        ArrayList<String> matches = new ArrayList<>();

        // 데이터에서 일치하는 모든 문자열을 찾아 리스트에 추가합니다.
        while (matcher.find()) {
            matches.add(matcher.group());
        }

        // 리스트를 배열로 변환합니다.
        String[] forecastArray = matches.toArray(new String[0]);

        // 결과를 출력합니다.
        for (String forecast : forecastArray) {
            System.out.println(forecast);
        }
        
        return forecastArray;
	}
	
	public Map<String,String> splitLineTP(String line){
		Map<String,String> returnVal = new HashMap<String,String>();
		
		String[] entity;
		
		entity=line.split("\\s+");
		if(entity.length==0) {
			return null;
		}
		
		for (int i=0;i<entity.length;i++) {
			returnVal.put("regId",entity[0]);
			returnVal.put("tmef",entity[2]);
            returnVal.put("tpMin",entity[6]);
			returnVal.put("tpMax",entity[7]);
		}
		
	
		
		System.out.println(returnVal.toString());
		
		return returnVal;
	}
	
}
