package com.team5.campscore.model;


import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Data
@Alias("weather")
public class WeatherDTO {
	
	private String rcode;
	private String addr;
	private String tp0;
	private String tp1;
	private String tp2;
	private String tp3;
	private String tp4;
	private String tp5;
	private String tp6;
	private String tp7;
	private String wc0;
	private String wc1;
	private String wc2;
	private String wc3;
	private String wc4;
	private String wc5;
	private String wc6;
	private String wc7;
	private String wcd0;
	private String wcd1;
	private String wcd2;
	private String wcd3;
	private String wcd4;
	private String wcd5;
	private String wcd6;
	private String wcd7;
	private String rp0;
	private String rp1;
	private String rp2;
	private String rp3;
	private String rp4;
	private String rp5;
	private String rp6;
	private String rp7;

}
