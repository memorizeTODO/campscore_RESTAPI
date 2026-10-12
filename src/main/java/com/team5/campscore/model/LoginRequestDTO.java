package com.team5.campscore.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("login")
public class LoginRequestDTO {
	private String memberID;
	private String memberPasswd;
}
