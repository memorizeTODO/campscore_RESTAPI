package com.team5.campscore.utilities;
import java.util.HashMap;
import java.util.Map;


public class CampingCategoryExtrator {
	public Map<String,String> findCampingCategoryData(String rawData) {
			
	
	        String[] categories= rawData.split(">");
	        if (categories.length==0) {
	        	return null;
	        }
			
	        // 데이터에서 일치하는 모든 문자열을 찾아 리스트에 추가합니다.
	        
	
	        // 리스트를 배열로 변환합니다.
	        Map<String,String> categoryMap =new HashMap<String,String>();
	
	        // 결과를 출력합니다.
	     
	        for (int i=0; i<categories.length;i++) {
	        	categoryMap.put("category"+i,categories[i].strip());
	        }
	        
	        System.out.println(categoryMap.toString());
	        
	        return categoryMap;
	
		}
}
