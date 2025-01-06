package com.team5.campscore.tools;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.WeatherDTO;
import com.team5.campscore.service.WeatherDAOImpl;
import com.team5.campscore.utilities.URLlib;
import com.team5.campscore.utilities.WeatherDataExtractor;

@RestController
public class WeatherTools {
	@Autowired
	private WeatherDAOImpl weatherService;
	
	
	public Map<String,String>  getWeatherWC(String rcode, String tmfc_param) {
    	
    	System.out.println(rcode);
        // 변수 설정
    	//Map<String,Map<String,Object>> formattedDataMaps = new HashMap<String,Map<String,Object>>(); 
    	Map<String,String> formattedDataMap = new HashMap<String,String>(); // db로 보낼 데이터맵
    	formattedDataMap.put("rcode",rcode);
    	
    	String tmfc;
    	
    	if (tmfc_param!=null) {
    		tmfc= tmfc_param;
    	}else {
    		tmfc="";
    	}
    	
    	
    	Map<String,String> params=new HashMap<String,String>();
    	Map<String,String> headers=new HashMap<String,String>();// get방식으로 요청 보낼 파라미터 모음
    	//
    	
    	String apiurl = "https://apihub.kma.go.kr/api/typ01/url/fct_afs_wl.php";
        params.put("authKey","zZ1CFjy9RvadQhY8vTb2fw");// 본인 서비스 키 입력
        
        params.put("reg", rcode );
        params.put("mode","0" );
        params.put("disp", "0" );
        params.put("tmfc",tmfc );
        headers.put("Connection", "keep-alive");
        
       
        URLlib urlCon = null;
        String result = null;
        boolean isSuccess=false;
        
        
        try {        
        	urlCon=new URLlib(apiurl,params); // api 주소, 파라미터(get), 헤더 값을 넣어 httpURLConnection 객체 할당
      
        	//urlCon.setRequestContentType("json");// 응답받고자하는 콘텐츠 타입 지정
        	urlCon.setRequestMethod("GET");// get방식으로 요청하도록 세팅
        	
        	
        	isSuccess=urlCon.getNetworkConnection();// 요청 실행
        	
            urlCon.readStreamToString("EUC-KR"); // 받아온 응답을 문자열로 저장
            result = urlCon.getResult(); // 응답 문자열을 가져옴
            
           
        } catch(IOException e) {
        	e.printStackTrace();
        } finally {
            urlCon.disconnect();
            if(isSuccess==false) {
        		return null;
        	}
        }
        
          
		/*
		 * String filePath = "c:\\Temp\\weatherWC.json";
		 * 
		 * 
		 * 
		 * try { FileWriter fileWriter = new FileWriter(filePath);
		 * fileWriter.write(result);
		 * 
		 * fileWriter.close(); } catch (IOException e) { e.printStackTrace(); }
		 */
        try {
        	WeatherDataExtractor w= new WeatherDataExtractor();
        	Map<String,String>dataMapAM= new HashMap<String,String>();
        	Map<String,String>dataMapPM= new HashMap<String,String>();
        	String am;
        	String pm;
        	
        	String[] lines = w.findWCData(result);
        	if (lines.length==0) {
        		return null;
        	}
        	
        	int cntAM = 3;
        	int cntPM = 3;
        	String dataAM="";
        	String dataPM="";
        	for(int i = 0; i<lines.length;i++) {
        		if(i%2==0) {
        			dataAM= w.splitLineWC(lines[i]).get("wc");
        			if(dataAM==null) {dataAM="";}
        			dataMapAM.put("wcAM"+cntAM, dataAM);
        			
        			dataAM= w.splitLineWC(lines[i]).get("wcd");
        			if(dataAM==null) {dataAM="";}
        			dataMapAM.put("wcdAM"+cntAM, dataAM);
        			
        			dataAM= w.splitLineWC(lines[i]).get("rp");
        			if(dataAM==null) {dataAM="";}
        			dataMapAM.put("rpAM"+cntAM, dataAM);
        			
        			cntAM++;
        		}else {
        			dataPM= w.splitLineWC(lines[i]).get("wc");
        			if(dataPM==null) {dataPM="";}
        			dataMapPM.put("wcPM"+cntPM, dataPM);
        			
        			dataPM= w.splitLineWC(lines[i]).get("wcd");
        			if(dataPM==null) {dataPM="";}
        			dataMapPM.put("wcdPM"+cntPM, dataPM);
        			
        			dataPM= w.splitLineWC(lines[i]).get("rp");
        			if(dataPM==null) {dataPM="";}
        			dataMapPM.put("rpPM"+cntPM, dataPM);
        			
        			cntPM++;
        		}
        	}
        	
        	for(int i=3; i<=7; i++) {
        		
        		formattedDataMap.put("wc"+i,dataMapAM.get("wcAM"+i)+"|"+dataMapPM.get("wcPM"+i));
        		formattedDataMap.put("wcd"+i,dataMapAM.get("wcdAM"+i)+"|"+dataMapPM.get("wcdPM"+i));
        		formattedDataMap.put("rp"+i,dataMapAM.get("rpAM"+i)+"|"+dataMapPM.get("rpPM"+i));
        		
        	}
        		
        		//formattedDataMap.put("wc"+i ,dataMap.get("wcd"));
        		//formattedDataMap.put("wc"+i ,dataMap.get("rp"));
        	
        	
        
        System.out.println(formattedDataMap.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    	return formattedDataMap;
    	
    	
    	//
    }
    
    public Map<String,String> getWeatherTP(String rcode, String tmfc_param){

    	// 변수 설정

    	Map<String,String> formattedDataMap = new HashMap<String,String> (); // db로 보낼 데이터맵
    	
    	//***날짜가져오는 알고리즘 -> 함수화 필요
    	String tmfc;
    	
    	if (tmfc_param!=null) {
    		tmfc= tmfc_param;
    	}else {
    		tmfc="";
    	}
    	
    	Map<String,String> params=new HashMap<String,String>();// get방식으로 요청 보낼 파라미터 모음
    	Map<String,String> headers=new HashMap<String,String>();
    	//
    	
    	String apiurl = "https://apihub.kma.go.kr/api/typ01/url/fct_afs_wc.php";
        params.put("authKey","zZ1CFjy9RvadQhY8vTb2fw");// 본인 서비스 키 입력
       
        params.put("reg", rcode);
        params.put("mode","0" );
        params.put("disp", "0" );
        params.put("tmfc", tmfc);
        headers.put("Connection", "keep-alive");
        
       
        URLlib urlCon = null;
        String result = null;
        boolean isSuccess=false;
        for (int i =0; i<3;i++) {
	        try {        
	        	urlCon=new URLlib(apiurl,params); // api 주소, 파라미터(get), 헤더 값을 넣어 httpURLConnection 객체 할당
	      
	        	//urlCon.setRequestContentType("json");;// 응답받고자하는 콘텐츠 타입 지정
	        	urlCon.setRequestMethod("GET");// get방식으로 요청하도록 세팅
	        	
	        	isSuccess=urlCon.getNetworkConnection();// 요청 실행
	        	
	        	
	            urlCon.readStreamToString("EUC-KR"); // 받아온 응답을 문자열로 저장
	            result = urlCon.getResult(); // 응답 문자열을 가져옴
	           
	            if(isSuccess==true) {
	        		break;
	        	}
	        }  catch(IOException e) {
	            e.printStackTrace();
	        } finally {
	            urlCon.disconnect();
	        }
        }

          
        String filePath = "c:\\Temp\\weatherTP.json";
        
        
		
		 try { FileWriter fileWriter = new FileWriter(filePath);
		 		fileWriter.write(result);
		  
		 		fileWriter.close(); 
		  }catch (IOException e) { 
		  		e.printStackTrace(); 
		  }
		 
			WeatherDataExtractor w= new WeatherDataExtractor();
        	Map<String,String>dataMap= new HashMap<String,String>();
        	
        	
        	
        	String[] lines = w.findTPData(result);
        	if (lines.length==0) {
        		return null;
        	}
        	
        	
        	for(int i = 0; i<5;i++) {
        		
        		dataMap.put("tpMin"+(i+3), w.splitLineTP(lines[i]).get("tpMin"));
        		dataMap.put("tpMax"+(i+3), w.splitLineTP(lines[i]).get("tpMax"));
        		
        	}
        	
        	for(int i=3; i<=7; i++) {
        		
        		formattedDataMap.put("tp"+i,dataMap.get("tpMin"+i)+"|"+dataMap.get("tpMax"+i));
        		
        	}

        
        System.out.println(formattedDataMap.toString());

    	return formattedDataMap;
    }

    // 샘플용 코드, tmFc값 문서보고 잘 세팅할 것  
    @RequestMapping("getWeatherAPI")
    public void getWeatherAPIc() {
    	getWeatherAPIList("202404090600");
    }
    
    public WeatherDTO getWeatherAPI(String regid,String tmfc_data){
    	
    	
    	WeatherDTO weatherDTO=null;
    	Map<String,String> weatherMap = null;
    	Map<String,String> weatherWCMap=null;
    	Map<String,String> weatherTPMap=null;
    	
    	try {
    		
    		for(int i=0; i<3;i++) {
    			if (regid=="11B20503") {
    				weatherWCMap=getWeatherWC(regid.substring(0,3)+"00000",tmfc_data);
    			}else {
    				weatherWCMap=getWeatherWC(regid.substring(0,4)+"0000",tmfc_data);
    			}
	    		if(weatherWCMap!=null ) {
	    			Thread.sleep(2000);
    				break;
    			}
    		}
    		for(int i=0; i<3;i++) {
    			weatherTPMap=getWeatherTP(regid,tmfc_data);
    			if(weatherTPMap!=null ) {
    				Thread.sleep(2000);
    				break;
    			}
    		}
    		
    		if(weatherWCMap==null||weatherTPMap==null) {
    			return null;
    		}
    		

	    	weatherDTO = new WeatherDTO();
	    	weatherMap = new HashMap<String,String>();
	   
	    	weatherMap.putAll(weatherTPMap);
        	weatherMap.putAll(weatherWCMap);
    		
	    	if(regid.matches("11B[0-9]{5}")) {weatherMap.put("addr","경기");}
	    	else if(regid.matches("11C1[0-9]{4}")) {weatherMap.put("addr","충북");}
	    	else if(regid.matches("11C2[0-9]{4}")) {weatherMap.put("addr","충남");}
	    	else if(regid.matches("11D1[0-9]{4}")) {weatherMap.put("addr","강원");}
	    	else if(regid.matches("11H1[0-9]{4}")) {weatherMap.put("addr","경북");}
	    	else if(regid.matches("11H2[0-9]{4}")) {weatherMap.put("addr","경남");}
	    	else if(regid.matches("11F1[0-9]{4}")) {weatherMap.put("addr","전북");}
	    	else if(regid.matches("11F2[0-9]{4}")) {weatherMap.put("addr","전남");}
	    	else if(regid.matches("11G0[0-9]{4}")) {weatherMap.put("addr","제주");}
	    	
	    	BeanUtils.populate(weatherDTO, weatherMap);
    	
    	}catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    	System.out.println(weatherDTO);
    	return weatherDTO;
    }
    
    public Map<String, Map<String, String>> getWeatherAPIList(String tmfc_data){
    	
    	Map<String,Map<String,String>> weatherMaps=new HashMap<String,Map<String,String>>();
    	Map<String,String>weatherMap= new HashMap<String,String>();
    	
    	Map<String,String> weatherWCMap;
    	Map<String,String> weatherTPMap;
    	
    	Map<String,String> rcodeMap = new HashMap<String,String>();
    	int i = 0;
    	rcodeMap.put("item"+(i++),"11B20503");//경기11B
		rcodeMap.put("item"+(i++),"11C10303");//충북11C1
		rcodeMap.put("item"+(i++),"11C20303");//충남11C2
		rcodeMap.put("item"+(i++),"11D10302");//강원11D
		rcodeMap.put("item"+(i++),"11H10502");//경북
		rcodeMap.put("item"+(i++),"11H20603");//경남
		rcodeMap.put("item"+(i++),"11F10201");//전북
		rcodeMap.put("item"+(i++),"11F20503");//전남
		rcodeMap.put("item"+i,"11G00201");//제주
		 
		

    	for ( String key : rcodeMap.keySet() ) {
    		weatherMap = new HashMap<String,String>();
    		System.out.println(key); 
    		if(key.equals("item0")==true||key.equals("item8")==true) {
    			weatherWCMap=getWeatherWC(rcodeMap.get(key).substring(0,3)+"00000",tmfc_data);
    			
    		}else {
    			weatherWCMap=getWeatherWC(rcodeMap.get(key).substring(0,4)+"0000",tmfc_data);
    		}
    		if(rcodeMap.get(key).matches("11B[0-9]{5}")) {weatherWCMap.put("addr","경기");}
    		if(rcodeMap.get(key).matches("11C1[0-9]{4}")) {weatherWCMap.put("addr","충북");}
    		if(rcodeMap.get(key).matches("11C2[0-9]{4}")) {weatherWCMap.put("addr","충남");}
    		if(rcodeMap.get(key).matches("11D1[0-9]{4}")) {weatherWCMap.put("addr","강원");}
    		if(rcodeMap.get(key).matches("11H1[0-9]{4}")) {weatherWCMap.put("addr","경북");}
    		if(rcodeMap.get(key).matches("11H2[0-9]{4}")) {weatherWCMap.put("addr","경남");}
    		if(rcodeMap.get(key).matches("11F1[0-9]{4}")) {weatherWCMap.put("addr","전북");}
    		if(rcodeMap.get(key).matches("11F2[0-9]{4}")) {weatherWCMap.put("addr","전남");}
    		if(rcodeMap.get(key).matches("11G0[0-9]{4}")) {weatherWCMap.put("addr","제주");}
        	weatherTPMap=getWeatherTP(rcodeMap.get(key),tmfc_data);
        	weatherMap.putAll(weatherTPMap);
        	weatherMap.putAll(weatherWCMap);
        	System.out.println(key);
        	weatherMaps.put(key,weatherMap);
    	}
    	
    	return weatherMaps;
    }
    
  
    
    public int updateWeatherMoveUpDate() { // 일자별 날씨 데이터들의 n일 후 날씨 컬럼들을 하루가 지날때마다 앞쪽으로 당겨주는 메소드 
    	
    	int result = 1;
    	//Map<String,Map<String,String>> weatherMaps;
    	//weatherMaps=getWeather();
    	List<WeatherDTO> getWList = weatherService.getWeatherList();//수정을 위해 db로 부터 받아온 데이터를 담은 날씨 dto들이 담긴 list
    	Map<String,Object> getWMap;//수정을 위해 db로 부터 받아온 데이터를 담은 날씨 dto를 변환시켜 저장할 map
    	Map<String,Object> tmpWMap;//수정을 위해 db로 부터 받아온 데이터를 담은 날씨 dto를 변환시켜 저장할 map
    	Map<String,Object> insWMap;// db로 수정 요청을 보내는데 사용할 dto를 변환시켜 저장할 map
    	WeatherDTO getW; // 수정을 위해 db로 부터 받아온 데이터를 담은 날씨 dto
    	WeatherDTO insW; // db로 수정 요청을 보내는데 사용할 dto
    	
    	
    	for(int i=0;i<getWList.size();i++) {
    		getW = getWList.get(i);
    		insW = new WeatherDTO();
    		getWMap= new HashMap<String,Object>();
    		tmpWMap= new HashMap<String,Object>();
    		insWMap= new HashMap<String,Object>();
    		
    		try {
				BeanUtils.populate(getWMap, BeanUtils.describe(getW));
				System.out.println(getWMap.toString());
				insWMap.put("rcode",(String)getWMap.get("rcode"));
				for(int j = 7; j > 0; j--) {
					String val1=(String)getWMap.get("wc"+j);
					if(val1==null) {val1="N/A";}
					insWMap.put("wc"+(j-1), val1); //ex:7일자 데이터를 6일자에
					String val2= (String)getWMap.get("wcd"+j);
					if(val2==null) {val2="N/A";}
					insWMap.put("wcd"+(j-1), val2);
					String val3= (String)getWMap.get("rp"+j);
					if(val3==null) {val3="N/A";}
					insWMap.put("rp"+(j-1), val3);
					String val4= (String)getWMap.get("tp"+j);
					if(val4==null) {val4="N/A";}
					insWMap.put("tp"+(j-1),val4);
					
				}	
				insWMap.put("wc7","N/A");
				insWMap.put("wcd7","N/A");
				insWMap.put("rp7","N/A");
				insWMap.put("tp7" ,"N/A");
				BeanUtils.populate(insW, insWMap);
					
				System.out.println("result="+insW.getRcode());
				result = weatherService.updateWeather(insW);
					
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result=0;
				continue;
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result=0;
				continue;
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result=0;
				continue;
			}
    	
    	}
    	return result;
    	
    }
    
    public int updateWeather(String regid, String tmfc_data) {
    	int returnVal=1;
    	
    	WeatherDTO weatherDTO=null;
    	try {
    		weatherDTO=getWeatherAPI(regid, tmfc_data);
    		returnVal=weatherService.updateWeather(weatherDTO);
    		if (returnVal==0) {
    			weatherService.insertWeather(weatherDTO);
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		return 0;
    	}
    	
    	return returnVal;
    }
    
    public int updateWeatherList(String tmfc_data) {
    	int result = 1;
    	Map<String,Map<String,String>> weatherMaps;
    	try {
        	
        	weatherMaps=getWeatherAPIList(tmfc_data);
	    	for (String key : weatherMaps.keySet()) {
	    		WeatherDTO weatherDTO = new WeatherDTO(); 
	    		//System.out.println(weatherMaps.get(key).toString());
	    		Map<String, String> weatherMap = weatherMaps.get(key);
	    		BeanUtils.populate(weatherDTO, weatherMap);
	    		//System.out.println( weatherDTO.getRcode());
	    	
	    		result=weatherService.updateWeather(weatherDTO);
	    		if (result==0) {weatherService.insertWeather(weatherDTO);}
	    		System.out.println(result);
	    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    		System.out.println(result);
    	}
    	
    	return result;
    }
    
    
    public int insertWeather(String regid,String tmfc_data){
	int returnVal=1;
    	
    	WeatherDTO weatherDTO=null;
    	try {
    		weatherDTO=getWeatherAPI(regid, tmfc_data);
    		returnVal=weatherService.insertWeather(weatherDTO);
    		if (returnVal==0) {
    			weatherService.updateWeather(weatherDTO);
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		return 0;
    	}
    	
    	return returnVal;
    }
    
    public int insertWeatherList(String tmfc_data) {
    	int result=1;
    	Map<String,Map<String,String>> weatherMaps;
    	try {
    	
    	weatherMaps= getWeatherAPIList(tmfc_data);
    	for(String key : weatherMaps.keySet()) {
    		WeatherDTO weatherDTO = new WeatherDTO(); 
    		System.out.println(weatherMaps.get(key).toString());
    		Map<String, String> weatherMap = weatherMaps.get(key);
    		BeanUtils.populate(weatherDTO, weatherMap);
    		System.out.println( weatherDTO.getRcode());
    	
    		result=weatherService.insertWeather(weatherDTO);
    		if (result==0) {weatherService.updateWeather(weatherDTO);}
    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    		
    	}
    	
    	return result;
    }
    
}
