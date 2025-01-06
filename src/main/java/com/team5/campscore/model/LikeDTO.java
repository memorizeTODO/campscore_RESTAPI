package com.team5.campscore.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("like")
public class LikeDTO {
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getPlaceID() {
		return placeID;
	}
	public void setPlaceID(int placeID) {
		this.placeID = placeID;
	}
	private String memberID;
	private int placeID;
	
}
