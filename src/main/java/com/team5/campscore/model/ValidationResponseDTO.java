package com.team5.campscore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResponseDTO {
    private boolean valid;   // true: 통과(사용 가능), false: 반려(조건 불충족 또는 중복)
    private String message;  // 통과 메시지 또는 반려 사유
}