package com.team5.campscore.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.CampingDAO;
import com.team5.campscore.model.CampingDTO;

@Service
public class CampingDAOImpl  implements CampingDAO {
	@Autowired
	CampingDAO dao;
	
	public int insertCamping(CampingDTO camping)  {
		int returnValue = 1;
		
		returnValue=dao.insertCamping(camping);
		
		return returnValue;
	}
	public List<CampingDTO> getCampingListByRegion(int start, String region, String sortType, String order) {
		
		return dao.getCampingListByRegion(start,region,sortType,order);
	};
	
	public List<CampingDTO> getCampingListByPlaceName(int start, String region, String sortType, String order, String placeName) {
		
		return dao.getCampingListByPlaceName(start,region,sortType,order,placeName);
	};
	public List<CampingDTO> getCampingList(int start, String region, String sortType, String order, String placeName, String category) {
		
		return dao.getCampingList(start,region,sortType,order,placeName,category);
	};
	
	
}
