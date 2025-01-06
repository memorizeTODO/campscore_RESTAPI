package com.team5.campscore.controller;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;



import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.team5.campscore.utilities.CampingCategoryExtrator;
import com.team5.campscore.utilities.URLlib;
import com.team5.campscore.utilities.WeatherDataExtractor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.team5.campscore.model.CampingDTO;
import com.team5.campscore.model.WeatherDTO;
import com.team5.campscore.service.*;

import org.apache.commons.beanutils.BeanUtils;
@RestController
@RequestMapping("/")
public class TestController {
	@Autowired
	WeatherDAOImpl weatherService;
	
	
    @RequestMapping("test1")
	public void testMethod() {
    	
        
        // 변수 설정
        String apiURL = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst";
        String serviceKey = "qB0RB3NhMOOhDD8j/0UaO514AWZMty+bIyJTvHYiWvIGRa0+W0MH0tZ/9QlcJw/BG1Sdu4J98qBpn7PucDdSUg=="; // 본인 서비스 키 입력
        String numOfRows = "10";
        String regId = "11B00000";
        String tmFc = "202403191800";
        String pageNo = "1";
        
        String dataType = "JSON";
		
		
		HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

		/*
		 * String urlStr = callBackUrl + "serviceKey=" + serviceKey + "&dataType=" +
		 * dataType + "&base_date=" + baseDate + "&base_time=" + baseTime +
		 * "&beach_num=" + beachNum;
		 */

        try {
        	StringBuilder urlBuilder = new StringBuilder(apiURL);
        	urlBuilder.append("?" + URLEncoder.encode("tmFc", "UTF-8") + "=" + URLEncoder.encode(tmFc, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(regId, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
            // 받으려는 타입
            
            URL url = new URL(urlBuilder.toString() );
            

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");
            
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);
            
            String filePath = "c:\\Temp\\test.json";
            
            try {
             FileWriter fileWriter = new FileWriter(filePath);
             fileWriter.write(result);
             
             fileWriter.close();
            } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
            }
    
            
           
            // 잘 출력되는지 확인하고 싶으면 아래 주석 해제
//            System.out.println(weather);


            
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        
        
		
	
	}
    @RequestMapping("test2")// 샘플용 코드, tmFc값 문서보고 잘 세팅할 것    
    private void test2Method() {
    
        // 변수 설정
        String apiURL = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst";
        String serviceKey = "qB0RB3NhMOOhDD8j/0UaO514AWZMty+bIyJTvHYiWvIGRa0+W0MH0tZ/9QlcJw/BG1Sdu4J98qBpn7PucDdSUg=="; // 본인 서비스 키 입력
        String numOfRows = "10";
        String regId = "11B00000";
        String tmFc = "202403250600";
        String pageNo = "1";
        
        String dataType = "JSON";
		
		
		HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

		/*
		 * String urlStr = callBackUrl + "serviceKey=" + serviceKey + "&dataType=" +
		 * dataType + "&base_date=" + baseDate + "&base_time=" + baseTime +
		 * "&beach_num=" + beachNum;
		 */

        try {
        	StringBuilder urlBuilder = new StringBuilder(apiURL);
        	urlBuilder.append("?" + URLEncoder.encode("tmFc", "UTF-8") + "=" + URLEncoder.encode(tmFc, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("regId", "UTF-8") + "=" + URLEncoder.encode(regId, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        	urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
            // 받으려는 타입
            
            URL url = new URL(urlBuilder.toString() );
            

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");
            
            stream = getNetworkConnection(urlConnection);
            result = readStreamToString(stream);
            
            String filePath = "c:\\Temp\\test.json";
            
            
            try {
             FileWriter fileWriter = new FileWriter(filePath);
             fileWriter.write(result);
             
             fileWriter.close();
            } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
            }
            
            try {
                JSONObject jsonObject = new JSONObject(result);

                // Example: Accessing specific values
                
                JSONObject response = jsonObject.getJSONObject("response");
                
                JSONObject body = response.getJSONObject("body");
                JSONObject items = body.getJSONObject("items");
                JSONObject item = items.getJSONArray("item").getJSONObject(0);
                
                System.out.println(item.toString());
				
				 int rnSt3Am = item.getInt("rnSt3Am"); 
				 int rnSt3Pm =item.getInt("rnSt3Pm"); 
				 String wf3Am = item.getString("wf3Am"); 
				 String wf3Pm = item.getString("wf3Pm");
				 
                

				
				 System.out.println("rnSt3Am: " + rnSt3Am);
				 System.out.println("rnSt3Pm: " +rnSt3Pm); 
				 System.out.println("wf3Am: " + wf3Am); 
				 System.out.println("wf3Pm: "+ wf3Pm);
				 

                // You can similarly access other values as needed

            } catch (Exception e) {
                e.printStackTrace();
            }
    
            
           
            // 잘 출력되는지 확인하고 싶으면 아래 주석 해제
//            System.out.println(weather);


            
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    	
    }
    
    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setDoInput(true);//서버의 응답을 inputStream으로 받겠다는 뜻

        if(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }

        return urlConnection.getInputStream();
    }

    /* InputStream을 전달받아 문자열로 변환 후 반환 */
    private String readStreamToString(InputStream stream) throws IOException{
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        String readLine;
        while((readLine = br.readLine()) != null) {
            result.append(readLine + "\n\r");
        }

        br.close();

        return result.toString();
    }
    
   
    	
        @RequestMapping("test3")
    	public void testMethod3() {
        	
            
            // 변수 설정
            String apiURL = "http://apis.data.go.kr/1360000/WthrWrnInfoService/getWthrPwn";
            String serviceKey = "qB0RB3NhMOOhDD8j/0UaO514AWZMty+bIyJTvHYiWvIGRa0+W0MH0tZ/9QlcJw/BG1Sdu4J98qBpn7PucDdSUg=="; // 본인 서비스 키 입력
            String numOfRows = "10";           
            String fromTmFc ="20240316";
            String toTmFc ="20240321";
            String stnId ="108";
            String pageNo = "1";
            String dataType = "JSON";
    		
    		
    		HttpURLConnection urlConnection = null;
            InputStream stream = null;
            String result = null;

    		/*
    		 * String urlStr = callBackUrl + "serviceKey=" + serviceKey + "&dataType=" +
    		 * dataType + "&base_date=" + baseDate + "&base_time=" + baseTime +
    		 * "&beach_num=" + beachNum;
    		 */

            try {
            	StringBuilder urlBuilder = new StringBuilder(apiURL);
            	urlBuilder.append("?" + URLEncoder.encode("fromTmFc", "UTF-8") + "=" + URLEncoder.encode(fromTmFc, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("toTmFc", "UTF-8") + "=" + URLEncoder.encode(toTmFc, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));            	
            	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8") + "=" + URLEncoder.encode(stnId, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
            	urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
                // 받으려는 타입
                
                URL url = new URL(urlBuilder.toString() );
                

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-type", "application/json");
                
                stream = getNetworkConnection(urlConnection);
                result = readStreamToString(stream);
                
                String filePath = "c:\\Temp\\test3.json";
                
                try {
                 FileWriter fileWriter = new FileWriter(filePath);
                 fileWriter.write(result);
                 
                 fileWriter.close();
                } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
                }
        
                
               
                // 잘 출력되는지 확인하고 싶으면 아래 주석 해제
//                System.out.println(weather);


                
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
            
            
            @RequestMapping("rusService")
        	public void rusService() {
            	
                
            	
            	
                // 변수 설정
                String apiURL = "http://apis.data.go.kr/B551011/RusService1/areaBasedList1";
                String serviceKey = "qB0RB3NhMOOhDD8j/0UaO514AWZMty+bIyJTvHYiWvIGRa0+W0MH0tZ/9QlcJw/BG1Sdu4J98qBpn7PucDdSUg=="; // 본인 서비스 키 입력
                String numOfRows = "10";           
                String areaCode="1";
                String pageNo = "1";
                String _type = "JSON";
        		
        		
        		HttpURLConnection urlConnection = null;
                InputStream stream = null;
                String result = null;

        		/*
        		 * String urlStr = callBackUrl + "serviceKey=" + serviceKey + "&dataType=" +
        		 * dataType + "&base_date=" + baseDate + "&base_time=" + baseTime +
        		 * "&beach_num=" + beachNum;
        		 */

                try {
                	StringBuilder urlBuilder = new StringBuilder(apiURL);
                	urlBuilder.append("?" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode(areaCode, "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));            	
                	urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("test", "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode(_type, "UTF-8"));
                	urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8"));
                    // 받으려는 타입
                    
                    URL url = new URL(urlBuilder.toString() );
                    

                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("Content-type", "application/json");
                    
                    stream = getNetworkConnection(urlConnection);
                    result = readStreamToString(stream);
                    
                    String filePath = "c:\\Temp\\rusService.json";
                    
                    try {
                     FileWriter fileWriter = new FileWriter(filePath);
                     fileWriter.write(result);
                     
                     fileWriter.close();
                    } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                    }
            
                    
                   
                    // 잘 출력되는지 확인하고 싶으면 아래 주석 해제
//                    System.out.println(weather);


                    
                } catch(IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
    		
    	
    	}
            
            
            
            @RequestMapping("go/testview")
            private RedirectView test5() {
            	  RedirectView redirectView = new RedirectView();
                  redirectView.setUrl("http://localhost:8080/views/test.html");
                  return redirectView;
            }
        
        
        @RequestMapping("main/search/kakaomap/keyword")//  
        //@CrossOrigin(origins="*") 
        private Map<String,Map<String,Object>> test5Method() {
        	
        	
        	HashMap<String,String> params=new HashMap<String,String>();// get방식으로 요청 보낼 파라미터 모음
        	HashMap<String,String> headers=new HashMap<String,String>();// 요청을 보낼때 같이 보낼 헤더
            
            String apiurl="https://dapi.kakao.com/v2/local/search/keyword.json"; //api 주소
            params.put("query","경기도 시흥시 카라반");
            
            params.put("numOfRows","10");
            params.put("pageNo","1");
    		
            headers.put("Authorization","KakaoAK adacc2024f0537f8eb428ee10db1dc20");//api의key값(카카오는 headers에,공공데이터는 params에)
            
    		URLlib urlCon = null;
            String result = null;
            boolean isSuccess=false;
    	
            try {        
            	urlCon=new URLlib(apiurl,params,headers); // api 주소, 파라미터(get), 헤더 값을 넣어 httpURLConnection 객체 할당
          
            	urlCon.setRequestContentType("application/json");// 응답받고자하는 콘텐츠 타입 지정
            	urlCon.setRequestMethod("GET");// get방식으로 요청하도록 세팅
            	for(int i=0;i<3;i++) {
            		isSuccess=urlCon.getNetworkConnection();// 요청 실행
            		if (isSuccess==true) {
            			break;
            		}
            	}
                urlCon.readStreamToString("UTF-8"); // 받아온 응답을 문자열로 저장
                result = urlCon.getResult(); // 응답 문자열을 가져옴
               
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                urlCon.disconnect();
            }
            
            // 로컬디스크에 json 파일로 저장
            String filePath = "c:\\Temp\\kakaoMap_result.json";
            try {
             FileWriter fileWriter = new FileWriter(filePath);
             fileWriter.write(result);
             
             fileWriter.close();
            } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
            }
            
            //json 추출
            Map<String,Map<String,Object>> resItems = new HashMap<String,Map<String,Object>>();
            JSONObject returnJSONObject = new JSONObject(); //장소 데이터 묶음들이 저장된 JSONArray를 JSONObject에 저장;// 리턴할 JSONObject 
            try {
                // Example: Accessing specific values
            	
                JSONArray returnJSONArray = new JSONArray();  
                
                JSONObject res = new JSONObject(result);
                
                JSONArray documents = res.getJSONArray("documents");
                JSONObject meta = res.getJSONObject("meta");
                
                
                int totalCount= meta.getInt("total_count") ; // 반환된 장소데이터 묶음 개수
                JSONObject resItemJSONObj;
                
                for(int i = 0; i < totalCount;i++) {
                	if(documents.length()==0) {
                		break;
                	}
                	Map<String,Object> itemMap = new HashMap<String,Object>();
                	JSONObject item = documents.getJSONObject(i);// resItem = 가져온 장소 데이터 묶음(JSONObject) 중 하나
                	itemMap.put("address_name",item.getString("address_name"));
                	itemMap.put("id", item.getString("id"));
                	itemMap.put("x", item.getDouble("x"));
                	itemMap.put("y", item.getDouble("y"));
                	
                	// 경도 위도 변환
                	TransformCoordinates tsc = new TransformCoordinates();
                	tsc.setCRSFactory();
                	tsc.setCoordinateTransformFactory();
                	tsc.transformCoords((double)itemMap.get("x"),(double)itemMap.get("y") );
                	System.out.println("address_name:" + (String)itemMap.get("address_name"));
                	System.out.println(itemMap.get("x").toString() + "  " + (String)itemMap.get("y").toString());
                	tsc.getResult();
                	//
                	//resItems.put("item_"+i, itemMap);
                	resItemJSONObj = new JSONObject(itemMap);//사용할 정보만 모아서 JSONObject로 변환하여 장소 데이터 묶음으로 재저장
                	returnJSONObject.put("items_"+i,resItemJSONObj);
                	resItems.put("items_"+i ,itemMap);
                }
              
                
                
                //System.out.println(returnJSONObject.toString());
				
                


            } catch (Exception e) {
                e.printStackTrace();
            }
        	
            return resItems;
            
            
        }
        
       
        
        
       // @RequestMapping("insert/weather")// 샘플용 코드, tmFc값 문서보고 잘 세팅할 것  
       // private int getWeatherToDB(String rcode){
        	
        	
       // }
        private String findWeatherCondition(String wc) {
        	if (wc==null|| wc.equals("")) {
        		return "";
        	}
        	if(wc.indexOf("맑")!=-1) {
            	return "C1";
            }else if(wc.indexOf("흐림")!=-1 || wc.indexOf("흐리고")!=-1) {
            	return "C2";
            }else if(wc.indexOf("구름많")!=-1) {
            	return "C3";
            }
        	
        	return "";
        }
        
        private String findWeatherConditionDetail(String wcd) {
        	String returnVal="";
        	if (wcd==null || wcd.equals("")) {
        		return returnVal;
        	}
        	
        	if(wcd.indexOf("비")!=-1) { //'비' 라는 문자를 찾았을때
        		if (wcd.length()!=0 ) {
        			returnVal+="/";
        		}
            	returnVal+="D1";
            }
        	if(wcd.indexOf("눈")!=-1) { //'눈' 이라는 문자를 찾았을때
        		if (wcd.length()!=0 ) {
        			returnVal+="/";
        		}
            	returnVal+="D2";
            }
        	if(wcd.indexOf("소나기")!=-1) { //'소나기' 라는 문자를 찾았을때
        		if (wcd.length()!=0 ) {
        			returnVal+="/";
        		}
            	returnVal+="D3";
            }
        	return returnVal;
        }
        
        
        

        
        
        
        
       
        	
        	
        

                   
}
