package com.team5.campscore.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("sight")
public class SightDTO {
	private String placeID;
	public String getPlaceID() {
		return placeID;
	}
	public void setPlaceID(String placeID) {
		this.placeID = placeID;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getRoadAddressName() {
		return roadAddressName;
	}
	public void setRoadAddressName(String roadAddressName) {
		this.roadAddressName = roadAddressName;
	}
	public String getPlaceUrl() {
		return placeUrl;
	}
	public void setPlaceUrl(String placeUrl) {
		this.placeUrl = placeUrl;
	}
	public String getPlaceImg() {
		return placeImg;
	}
	public void setPlaceImg(String placeImg) {
		this.placeImg = placeImg;
	}
	public double getPlaceLat() {
		return placeLat;
	}
	public void setPlaceLat(double placeLat) {
		this.placeLat = placeLat;
	}
	public double getPlaceLong() {
		return placeLong;
	}
	public void setPlaceLong(double placeLong) {
		this.placeLong = placeLong;
	}
	public String getPlaceCategoryDetail() {
		return placeCategoryDetail;
	}
	public void setPlaceCategoryDetail(String placeCategoryDetail) {
		this.placeCategoryDetail = placeCategoryDetail;
	}
	private String placeName;
	private String addressName;
	private String roadAddressName;
	private String placeUrl;
	private String placeImg;
	private double placeLat;
	private double placeLong;
	private String placeCategoryDetail;
}
