package com.team5.campscore.utilities;

import com.team5.campscore.model.ValidationResponseDTO;

public class MemberValidationUtils {

    // SQL 인젝션 및 위험한 특수문자 패턴 정의 (', ", ;, --, /*, */ 등)
	private static final String DANGEROUS_PATTERN = ".*(['\";]|--|/\\*|\\*/).*";

    // 1. 아이디 검증
    // - 4자 이상 12자 이하
    // - 영문, 숫자로만 조합
    // - 첫자는 무조건 영문
    public static ValidationResponseDTO validateMemberID(String memberID) {
        if (memberID == null || memberID.isBlank()) {
            return new ValidationResponseDTO(false, "아이디를 입력해주세요.");
        }

        // 정규식: 영문 시작([a-zA-Z]), 이후 영문/숫자 조합으로 전체 4~12자
        // 첫자(1자) + 뒤 3~11자 = 총 4~12자 -> ^[a-zA-Z][a-zA-Z0-9]{3,11}$
        String regex = "^[a-zA-Z][a-zA-Z0-9]{3,11}$";
        if (!memberID.matches(regex)) {
            return new ValidationResponseDTO(false, "아이디는 영문으로 시작해야 하며, 영문과 숫자 조합으로 4~12자여야 합니다.");
        }

        return new ValidationResponseDTO(true, "사용 가능한 아이디 형식입니다.");
    }

    // 2. 비밀번호 검증
    // - 10자리 이상 20자리 이하
    // - 영문, 숫자, Shift+숫자 특수문자(!@#$%^&*())가 '모두' 포함
    // - SQL 인젝션 등 위험 기호 포함 시 반려
    public static ValidationResponseDTO validatePassword(String password) {
        if (password == null || password.isBlank()) {
            return new ValidationResponseDTO(false, "비밀번호를 입력해주세요.");
        }

        // 길이 체크 (10 ~ 20자)
        if (password.length() < 10 || password.length() > 20) {
            return new ValidationResponseDTO(false, "비밀번호는 10자리 이상 20자리 이하이어야 합니다.");
        }

        // SQL 인젝션 위험 기호 포함 여부 검사
        if (password.matches(DANGEROUS_PATTERN)) {
            return new ValidationResponseDTO(false, "보안상 허용되지 않는 특수문자(', \", ;, -- 등)가 포함되어 있습니다.");
        }

        // 필수 조합 체크 (영문, 숫자, Shift+숫자 특수문자 !@#$%^&*())
        boolean hasEnglish = password.matches(".*[a-zA-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()].*");

        if (!hasEnglish || !hasNumber || !hasSpecial) {
            return new ValidationResponseDTO(false, "비밀번호는 영문, 숫자, 특수문자(!@#$%^&*())를 모두 포함해야 합니다.");
        }

        return new ValidationResponseDTO(true, "안전한 비밀번호 형식입니다.");
    }

    // 3. 닉네임 검증
    // - 순수 영어 기준 20자 제한 (한글 및 특수문자 1자 = 영어 2자로 취급)
    // - 첫자는 한글 또는 영어만 허용
    // - SQL 인젝션 위험 기호 포함 시 반려
    public static ValidationResponseDTO validateNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            return new ValidationResponseDTO(false, "닉네임을 입력해주세요.");
        }

        // 첫자 검증 (한글 또는 영문만 허용)
        char firstChar = nickname.charAt(0);
        boolean isFirstValid = (firstChar >= 'a' && firstChar <= 'z') || 
                               (firstChar >= 'A' && firstChar <= 'Z') || 
                               (firstChar >= '\uAC00' && firstChar <= '\uD7A3');
        if (!isFirstValid) {
            return new ValidationResponseDTO(false, "닉네임의 첫 글자는 영문 또는 한글이어야 합니다.");
        }

        // SQL 인젝션 위험 기호 포함 여부 검사
        if (nickname.matches(DANGEROUS_PATTERN)) {
            return new ValidationResponseDTO(false, "보안상 허용되지 않는 특수문자가 포함되어 있습니다.");
        }

        // 가중치 길이 계산 (한글 및 특수문자 = 2, 영어/숫자 등 = 1)
        int totalWeight = 0;
        for (int i = 0; i < nickname.length(); i++) {
            char c = nickname.charAt(i);
            boolean isKorean = (c >= '\uAC00' && c <= '\uD7A3');
            boolean isSpecial = !Character.isLetterOrDigit(c); // 알파벳/숫자가 아니면 특수문자로 취급

            if (isKorean || isSpecial) {
                totalWeight += 2;
            } else {
                totalWeight += 1;
            }
        }

        if (totalWeight > 20) {
            return new ValidationResponseDTO(false, "닉네임 글자 수 제한을 초과했습니다. (한글 및 특수문자는 2글자로 취급됩니다.)");
        }

        return new ValidationResponseDTO(true, "사용 가능한 닉네임 형식입니다.");
    }
    
    // 4. 사업자 등록 번호 검증
    // - 10자리 숫자 여부 확인 (하이픈 자동 제거 후 검사)
    // - 국세청 사업자등록번호 공식 체크섬 알고리즘 적용
    public static ValidationResponseDTO validateBusinessNumber(String businessNumber) {
        if (businessNumber == null || businessNumber.isBlank()) {
            return new ValidationResponseDTO(false, "사업자 등록 번호를 입력해주세요.");
        }

        // 하이픈(-) 제거
        String cleanNum = businessNumber.replaceAll("-", "").trim();

        // 10자리 숫자 여부 정규식 검사
        if (!cleanNum.matches("^\\d{10}$")) {
            return new ValidationResponseDTO(false, "사업자 등록 번호는 10자리 숫자여야 합니다.");
        }

        // 사업자등록번호 체크섬 공식 알고리즘
        int[] weights = {1, 3, 7, 1, 3, 7, 1, 3, 5};
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += (cleanNum.charAt(i) - '0') * weights[i];
        }

        // 9번째 자리에 5를 곱한 후 10으로 나눈 몫을 더함
        sum += ((cleanNum.charAt(8) - '0') * 5) / 10;

        int remainder = (10 - (sum % 10)) % 10;
        int lastDigit = cleanNum.charAt(9) - '0';

        if (remainder != lastDigit) {
            return new ValidationResponseDTO(false, "유효하지 않은 사업자 등록 번호입니다.");
        }

        return new ValidationResponseDTO(true, "유효한 사업자 등록 번호 형식입니다.");
    }
}