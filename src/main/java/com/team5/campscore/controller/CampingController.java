package com.team5.campscore.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.annotations.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.CampingDTO;
import com.team5.campscore.service.CampingDAOImpl;
import com.team5.campscore.utilities.CampingCategoryExtrator;
import com.team5.campscore.model.SearchResponse;

import com.team5.campscore.utilities.PlaceRcodeMapBuilder;
import com.team5.campscore.utilities.URLlib;

@RestController
@RequestMapping("/")
public class CampingController {
	@Autowired
	CampingDAOImpl campingService;
	
	@GetMapping("/get/campinglist")
	public ResponseEntity<SearchResponse<CampingDTO>> getCampingToView(
	        @RequestParam(name = "page", defaultValue = "1") int page,
	        @RequestParam(name = "place-query", required = false, defaultValue = "") String placeName,
	        @RequestParam(name = "camp-region", required = false, defaultValue = "") String region,
	        @RequestParam(name = "sort-type", required = false, defaultValue = "place-name") String sortType,
	        @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
	        @RequestParam(name = "camp-type", required = false) List<String> campTypeList
	) {
	    // 1. sort-type 검증
	    if (!sortType.equals("place-name") && !sortType.equals("weather-score")) {
	        sortType = "place-name";
	    }

	    // 2. order 검증
	    if (!order.equals("asc") && !order.equals("desc")) {
	        order = "asc";
	    }

	    // 3. 캠핑 종류(categoryList) 가공
	    List<String> categoryList = null;
	    if (campTypeList != null && !campTypeList.isEmpty()) {
	        // "ALL"이 포함되어 있거나 비어있지 않은 경우에만 리스트 유효화
	        boolean hasAll = campTypeList.stream().anyMatch(val -> val.equals("ALL") || val.isEmpty());
	        if (!hasAll) {
	            categoryList = campTypeList; // 예: ["카라반", "글램핑장"] 그대로 전달됨
	        }
	    }

	    System.out.println("placeName = " + placeName);
	    System.out.println("region = " + region + ", sortType = " + sortType + ", order = " + order);
	    System.out.println("categoryList = " + categoryList);

	    // 4. 서비스 호출
	    SearchResponse<CampingDTO> response = campingService.getCampingListWithPaging(
	            page, region, sortType, order, placeName, categoryList
	    );

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping("insert/camping")
	public void insertCamping() {
		
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
		    	   params.put("query", prMap.get(key)+" 캠핑장" );
		    	   params.put("category_group_code", "AD5");
		           headers.put("Authorization", "KakaoAK b95fe7ff7e17afbd3618c81bad9d439e");
		        	
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
	                	if(categoryMap.get("category2")==null || !categoryMap.get("category2").equals("야영,캠핑장")) {
	                		continue; 
	                	}
	                	
	                			
	                	CampingDTO camping= new CampingDTO();
	                	
	                	camping.setPlaceID(item.getString("id"));
	                	if(checkDuplicateId.get(camping.getPlaceID())!=null) {
	                		continue;
	                	}
	                	camping.setPlaceName(item.getString("place_name"));
	                	camping.setAddressName(item.getString("address_name"));
	                	camping.setRoadAddressName(item.getString("road_address_name"));
	                	camping.setPlaceUrl(item.getString("place_url"));
	                	camping.setPlaceImg("");
	                	System.out.println(item.getDouble("x"));
	                	camping.setPlaceLong(item.getDouble("x"));
	                	System.out.println(item.getDouble("y"));
	                	camping.setPlaceLat(item.getDouble("y"));
	                	camping.setPlaceCategoryDetail(categoryMap.get("category3"));
	                	
	                	
	                	campingService.insertCamping(camping);
	                	
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
