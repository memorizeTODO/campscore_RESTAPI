package com.team5.campscore.dao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team5.campscore.model.CampingDTO;
import com.team5.campscore.model.SightDTO;
import com.team5.campscore.service.CampingDAOImpl;
import com.team5.campscore.utilities.CampingCategoryExtrator;
import com.team5.campscore.utilities.PlaceRcodeMapBuilder;
import com.team5.campscore.utilities.URLlib;

@Mapper
public interface SightDAO {
	public int insertSight(SightDTO sightDTO);
	public List<SightDTO> getSightList(@Param("region")String region,@Param("start")int  start);
}
