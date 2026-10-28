package com.team5.campscore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.JwtResponseDTO;
import com.team5.campscore.model.LoginRequestDTO;
import com.team5.campscore.security.JwtTokenProvider;
import com.team5.campscore.security.MemberDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
	public class AuthenticationController {
	
	    private final AuthenticationManager authenticationManager;
	    private final JwtTokenProvider jwtTokenProvider;
	
	    @PostMapping("login")
	    public ResponseEntity<?> login(@RequestBody LoginRequestDTO requestDTO) {
	        // 1. 사용자가 보낸 아이디와 패스워드를 기반으로 인증 토큰 객체를 만들어 AuthenticationManager에 전달
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(requestDTO.getMemberID(), requestDTO.getMemberPasswd())
	        );
	        
	        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
	
	        // 2. 인증에 성공했다면, 해당 유저의 ID를 기반으로 JWT 토큰을 발급
	        String token = jwtTokenProvider.createToken(authentication.getName());
	
	        // 3. 발급받은 토큰을 응답 객체(DTO)에 담아 클라이언트에 반환
	     // [수정] memberType(권한/유형)을 포함하여 응답 DTO 생성
	        JwtResponseDTO responseDTO = new JwtResponseDTO(
	                token, 
	                "Bearer", 
	                memberDetails.getUsername(),      
	                memberDetails.getMemberNickname(), 
	                memberDetails.getMemberType()       // <-- 회원 유형 추가 전달!
	        );

	        return ResponseEntity.ok(responseDTO);
    }
}