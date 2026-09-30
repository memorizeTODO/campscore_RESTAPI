package com.team5.campscore.model;

import org.apache.ibatis.type.Alias;

//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@Alias("camping")
public class CampingDTO {
  
	private String placeID;
	private String placeName;
	private String addressName;
	private String roadAddressName;
	private String placeUrl;
	private String placeImg;
	private double placeLat;
	private double placeLong;
	private String placeCategoryDetail;
	private String region;
}