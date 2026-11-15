package com.team5.campscore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// 회원 정보 변경을 위한 수정권한 부여 토큰
public class VerificationTokenResponseDTO {
    private String VerificationToken;
    private String message;
}