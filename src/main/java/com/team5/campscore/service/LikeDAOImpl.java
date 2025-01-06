package com.team5.campscore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.campscore.dao.LikeDAO;
import com.team5.campscore.model.LikeDTO;

@Service
public class LikeDAOImpl implements LikeDAO {
	@Autowired
	LikeDAO dao;
	
	public int insertLike(LikeDTO likeDTO){
		try {
			dao.insertLike(likeDTO);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	public int getCount(LikeDTO likeDTO) {
		int returnVal;
		try {
			returnVal = dao.getCount(likeDTO);
		}catch(Exception e) {
			returnVal = -1;//좋아요 갯수가 0일 수도 있으므로 특수하게 실패시 -1를 리턴
		}
		return returnVal;
		
	}
	
	public int deleteLike(LikeDTO likeDTO){
		try {
			dao.deleteLike(likeDTO);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	
}
