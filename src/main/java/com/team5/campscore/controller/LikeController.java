package com.team5.campscore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.LikeDTO;
import com.team5.campscore.service.LikeDAOImpl;

@RestController
@RequestMapping("/")
public class LikeController {
	@Autowired
	LikeDAOImpl likeService;
	
	@PostMapping(value="insert/like")
	int insertLike(@RequestParam Map<String,Object>params){
		int returnVal = 0;
		int placeID = -1;
		String memberID = null;
		
		if(params.get("member_id")!=null) {
			memberID = (String)params.get("member_id");
		}
		if(params.get("place_id")!=null) {
			placeID = (Integer)params.get("place_id");
		}
		
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setMemberID(memberID);
		likeDTO.setPlaceID(placeID);
		
		returnVal = likeService.insertLike(likeDTO);
		
		return returnVal;
	}
	
	@PostMapping(value="delete/like")
	int deleteLike(@RequestParam Map<String,Object>params){
		int returnVal = 0;
		int placeID = -1;
		String memberID = null;
		
		if(params.get("member_id")!=null) {
			memberID = (String)params.get("member_id");
		}
		if(params.get("place_id")!=null) {
			placeID = (Integer)params.get("place_id");
		}
		
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setMemberID(memberID);
		likeDTO.setPlaceID(placeID);
		
		returnVal = likeService.deleteLike(likeDTO);
		
		return returnVal;
	}
	
	@PostMapping(value="get_cnt/like")
	int getLikeCount(@RequestParam Map<String,Object>params){
		int returnVal = 0;
		int placeID = -1;
		
		if(params.get("place_id")!=null) {
			placeID = (Integer)params.get("place_id");
		}
		
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setPlaceID(placeID);
		
		returnVal = likeService.getCount(likeDTO);
		
		return returnVal;
	}
}
