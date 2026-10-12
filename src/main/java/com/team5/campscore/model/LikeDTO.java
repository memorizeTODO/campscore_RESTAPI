package com.team5.campscore.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("like")
public class LikeDTO {
	
	private String memberID;
	private int placeID;
	
}
