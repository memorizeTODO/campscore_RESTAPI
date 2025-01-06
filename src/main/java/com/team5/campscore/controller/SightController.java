package com.team5.campscore.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.SightDTO;
import com.team5.campscore.service.SightDAOImpl;


@RestController
public class SightController {
	
	@Autowired
	public SightDAOImpl sightService;
	
	@GetMapping(value ="get/sightlist")
	ResponseEntity<Map<String, Map<String, Object>>> getSightToView(@RequestParam Map<String,String> params){
		int page; 
		String region= null;
		String placeID = null;
		String order = "asc";
		
		if(params.get("page")==null) {
			page=1;
		}else{
			try {
				page=Integer.parseInt(params.get("page"));
				
			}
			catch (NumberFormatException e) {
				page=1;
			}
		}
		if(params.get("region")!=null) {
			region=params.get("region");
		}
		
		
		if(params.get("order")!=null) {
			switch(params.get("order")) {
				case "asc":	case "desc":
					order=params.get("order");
			}
			
		}
		/*
		 * if (params.get("placeID") == null){ return null; }
		 */
		
		
		int start = (page-1)*8 ;
		
		System.out.println("region="+region);
		
		Map<String, Map<String, Object>> sightMaps= new HashMap<String, Map<String, Object>>();
		List<SightDTO> sightList= new ArrayList<SightDTO>();
		
		sightList=sightService.getSightList(region,start);
		
		
		  for(int i=0;i<sightList.size();i++) { 
			  
			  Map<String, Object> sightMap = new HashMap<String, Object>();
		  
			  try { 
				  BeanUtils.populate(sightMap, BeanUtils.describe(sightList.get(i)));
			  } 
			  catch (IllegalAccessException e) {
				  e.printStackTrace(); 
			  } catch (InvocationTargetException e) { 
				  e.printStackTrace();
			  } catch (NoSuchMethodException e) { 
				  e.printStackTrace(); 
			  } 
			  System.out.println(sightMap.toString());
			  
			  System.out.println(sightMap.get("placeID"));
			  
			  sightMaps.put("item"+i,sightMap); 
		  }
		 
		
		return new ResponseEntity<>(sightMaps, HttpStatus.OK);
	}	
}
