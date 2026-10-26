package com.team5.campscore.model;

import lombok.Data;

@Data
public class MemberDTO {
	private String memberID;    // 이메일
    private String memberPasswd; // 비밀번호
    private String memberNickname; // 닉네임
    private String memberName;
    private String memberEmail;    // 이메일
    private String memberType;     // 'general' 또는 'business' 또는 administrator(관리자 가입 관련된것은 관리자 페이지와 함께 추후 따로 만들 예정 )
    private String memberPhone1;
    private String memberPhone2;
    private String memberPhone3;
    private String memberAddress1;       // 거주지 주소 1
    private String memberAddress2;       // 상세 주소 2
    private String birthDate;      // 생년월일 6자리
    private String preferredRegion;// 선호지역
    private String businessNumber;  // 사업자 등록번호 (업주 회원 전용)
}
