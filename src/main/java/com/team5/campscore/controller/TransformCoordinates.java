package com.team5.campscore.controller;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;


public class TransformCoordinates {
	
	private CRSFactory crsFactory=null;
	private CoordinateTransformFactory ctFactory;
	private CoordinateReferenceSystem from;
	private CoordinateReferenceSystem to;
	private ProjCoordinate result;
	
	void setCRSFactory() {
		if(crsFactory==null) {
			crsFactory = new CRSFactory();
		}
		from = crsFactory.createFromName("epsg:4326");
		to = crsFactory.createFromParameters("epsg:4116",
			    "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs");
		
	}
	
	void setCoordinateTransformFactory() {
		if(ctFactory== null) {
			ctFactory = new CoordinateTransformFactory();
		}
		
	}
	void transformCoords(double lon, double lat) {
		CoordinateTransform transform = ctFactory.createTransform(from, to);
		// `result` is an output parameter to `transform()`
		result = new ProjCoordinate();
		transform.transform(new ProjCoordinate(lon, lat), this.result);
	}
	ProjCoordinate getResult() {
		System.out.println(result.toShortString());
		return result;
	}
	
}
