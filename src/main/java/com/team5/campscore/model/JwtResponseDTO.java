package com.team5.campscore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자 (JSON 직렬화/역직렬화용)
@AllArgsConstructor // 모든 필드를 받는 생성자 (컨트롤러에서 객체 생성 시 편리)
public class JwtResponseDTO {
	private String accessToken;   // 실제 JWT 토큰 값
    private String tokenType = "Bearer"; // 토큰 타입 (보통 "Bearer" 사용)
}
