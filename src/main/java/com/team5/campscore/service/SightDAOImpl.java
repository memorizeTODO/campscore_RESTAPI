package com.team5.campscore.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.SightDAO;
import com.team5.campscore.model.SightDTO;

@Service
public class SightDAOImpl implements SightDAO {
	@Autowired
	private SightDAO dao;
	
	public int insertSight(SightDTO sightDTO){
		int returnVal =1;
		dao.insertSight(sightDTO);
		System.out.println(returnVal);
		return returnVal;
	};
	public List<SightDTO> getSightList(String region, int start) {
		List<SightDTO> sList=null;
		try {
			 sList=dao.getSightList(region,start);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return sList;
	}
}
