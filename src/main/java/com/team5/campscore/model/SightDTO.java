package com.team5.campscore.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("sight")
public class SightDTO {
	private String placeID;
	private String placeName;
	private String addressName;
	private String roadAddressName;
	private String placeUrl;
	private String placeImg;
	private double placeLat;
	private double placeLong;
	private String placeCategoryDetail;
	private long totalCount;
}
