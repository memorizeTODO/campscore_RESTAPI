package com.team5.campscore.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.CampingDTO;
import com.team5.campscore.model.SightDTO;
import com.team5.campscore.service.SightDAOImpl;
import com.team5.campscore.utilities.CampingCategoryExtrator;
import com.team5.campscore.utilities.PlaceRcodeMapBuilder;
import com.team5.campscore.utilities.URLlib;

@Component
public class SightTools {
	@Autowired
	public SightDAOImpl sightService;
	
	
	
	
	@RequestMapping("insert/sight")
	public void insertSight() {
		insertSightByCategory("CT1");
		insertSightByCategory("AT4");
	}
	
	public void insertSightByCategory(String category_group_code) {
		
		String apiurl = "https://dapi.kakao.com/v2/local/search/keyword.json";

        URLlib urlCon = null;
        String result = null;
       
        PlaceRcodeMapBuilder prMapBuilder= new PlaceRcodeMapBuilder();
        Map<String,String> prMap = prMapBuilder.getPlaceRcodeMap();
        Map<String,String> checkDuplicateId=new HashMap<String,String>();
        
        for(String key:prMap.keySet()) {
        	boolean isEnd=false;
        	int cnt=1;
	        while(isEnd!=true) {
	        	
	        	
		        try { 
		           Map<String,String> params=new HashMap<String,String>();
		    	   Map<String,String> headers=new HashMap<String,String>();
		    	   params.put("query", prMap.get(key)+"" );
		    	   params.put("category_group_code", category_group_code);
		           headers.put("Authorization", "KakaoAK adacc2024f0537f8eb428ee10db1dc20");
		        	
		           params.put("page", Integer.toString(cnt) );
		           urlCon=new URLlib(apiurl,params,headers); // api 주소, 파라미터(get), 헤더 값을 넣어 httpURLConnection 객체 할당
		      
		           //urlCon.setRequestContentType("json");// 응답받고자하는 콘텐츠 타입 지정
		           urlCon.setRequestMethod("GET");// get방식으로 요청하도록 세팅
		       
		            urlCon.getNetworkConnection();// 요청 실행
		            urlCon.readStreamToString("UTF8"); // 받아온 응답을 문자열로 저장
		            result = urlCon.getResult(); // 응답 문자열을 가져옴
		            
		            
		            
		           
		        } catch(IOException e) {
		            e.printStackTrace();
		        } finally {
		            urlCon.disconnect();
		        }
		        
		        try {
	                JSONObject jsonObject = new JSONObject(result);
	
	                // Example: Accessing specific values
	                
	                JSONArray documents = jsonObject.getJSONArray("documents");
	                JSONObject metaData =  jsonObject.getJSONObject("meta");
	                
	                isEnd=metaData.getBoolean("is_end");
	                System.out.println(isEnd);
	                	
	                CampingCategoryExtrator cce= new CampingCategoryExtrator();
	                Map<String,String> categoryMap;
	                
	                if(documents==null) {
	                	System.out.println("errorType="+jsonObject.getString("errorType"));
	                	continue;
	                }
	                
	                for(int i=0;i<documents.length();i++) {
	                	
	                	
	                	JSONObject item = documents.getJSONObject(i);
	                	
	                	categoryMap=cce.findCampingCategoryData(item.getString("category_name"));
	                	
	                			
	                	SightDTO sightDTO= new SightDTO();
	                	
	                	sightDTO.setPlaceID(item.getString("id"));
	                	if(checkDuplicateId.get(sightDTO.getPlaceID())!=null) {
	                		continue;
	                	}
	                	sightDTO.setPlaceName(item.getString("place_name"));
	                	sightDTO.setAddressName(item.getString("address_name"));
	                	sightDTO.setRoadAddressName(item.getString("road_address_name"));
	                	sightDTO.setPlaceUrl(item.getString("place_url"));
	                	sightDTO.setPlaceImg("");
	                	sightDTO.setPlaceLong(item.getDouble("x"));
	                	sightDTO.setPlaceLat(item.getDouble("y"));
	              
	                	sightDTO.setPlaceCategoryDetail(categoryMap.get("category2"));
	                	
	                	
	                	sightService.insertSight(sightDTO);
	                	
	                	/*System.out.println("camp_id: " + camp_id);
	    				System.out.println("camp_name: " +camp_name); 
	    				System.out.println("camp_address name: " +camp_address name); 
	    				System.out.println("camp_ road address name: " +camp_ road address name); 
	    				System.out.println("camp_url: " +camp_url); 
	    				System.out.println("camp_imgl: " +camp_img); 
	    				System.out.println("camp_lat: " +camp_lat); 
	    				System.out.println("camp_long: " +camp_long); */
	                	checkDuplicateId.put(item.getString("id"), item.getString("id"));
	                }
	                // You can similarly access other values as needed
	                Thread.sleep(1000);//1초간 휴식
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
		        cnt++;
		        
		        if (isEnd==true||cnt==46) {
		            break;
		        }
	        }
        }
	}
}
