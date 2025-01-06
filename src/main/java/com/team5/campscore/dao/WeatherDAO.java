package com.team5.campscore.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.team5.campscore.model.WeatherDTO;

@Mapper
public interface WeatherDAO {
	public int insertWeather(WeatherDTO w); 
	public int updateWeather(WeatherDTO w);
	public WeatherDTO getWeather(String region);
	public List<WeatherDTO> getWeatherList();
}
