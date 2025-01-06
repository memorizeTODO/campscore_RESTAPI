package com.team5.campscore.service;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.team5.campscore.dao.WeatherDAO;
import com.team5.campscore.model.WeatherDTO;

import lombok.RequiredArgsConstructor;

@Service
public class WeatherDAOImpl implements WeatherDAO {
	@Autowired
	private WeatherDAO dao;
	
	public int insertWeather(WeatherDTO w) {
	    try {
	        // insert 쿼리 실행
	    	System.out.println(w.getAddr());
	    	int rowsAffected = dao.insertWeather(w);
	    	if (rowsAffected == 0) {
	    		System.out.println("insert failed");
	    		
	            return 0;
	        }
	        
	    } catch (PersistenceException e) {
	        // MyBatis의 PersistenceException 처리
	    	
	        return 0;
	    }
	    return 1;
	}
	
	public List<WeatherDTO> getWeatherList() {
		
		 
		return dao.getWeatherList();
		
	}
	public WeatherDTO getWeather(String region) {

		return dao.getWeather(region);
		
	}
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateWeather(WeatherDTO w) {
    	try {
	        // insert 쿼리 실행
    		int rowsAffected = dao.updateWeather(w);
    		if (rowsAffected == 0) {
	    		System.out.println("updated failed");
	            return 0;
	        }
	        
	    } catch (PersistenceException e) {
	        // MyBatis의 PersistenceException 처리
	        return 0;
	    }
    	
    	return 1;
	}
}
