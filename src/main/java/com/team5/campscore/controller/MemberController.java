package com.team5.campscore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.MemberDTO;
import com.team5.campscore.model.ValidationResponseDTO;
import com.team5.campscore.service.MemberDAOImpl;
import com.team5.campscore.utilities.MemberValidationUtils;

import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberDAOImpl memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("signup")
    public ResponseEntity<String> signupMember(@RequestBody MemberDTO memberDTO) {
        try {
            // 이메일과 아이디 동기화 처리 (필요시)
            if (memberDTO.getMemberID() != null && memberDTO.getMemberEmail() == null) {
                memberDTO.setMemberEmail(memberDTO.getMemberID());
            }
            
            String rawPassword = memberDTO.getMemberPasswd(); 
            
            // 2. BCrypt로 안전하게 암호화(해싱)
            String encodedPassword = passwordEncoder.encode(rawPassword);
            
            // 3. 암호화된 비밀번호를 다시 DTO에 세팅
            memberDTO.setMemberPasswd(encodedPassword);

            memberService.insertMember(memberDTO);
            return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("회원가입 실패: " + e.getMessage());
        }
    }
    // 1. 아이디 중복확인
    @PostMapping("chk-id-dup")
    public ResponseEntity<ValidationResponseDTO> checkIdDup(@RequestBody MemberDTO memberDTO) {
        // 1. 형식 및 규칙 검증 먼저 수행
        ValidationResponseDTO validationResult = MemberValidationUtils.validateMemberID(memberDTO.getMemberID());
        if (!validationResult.isValid()) {
            return ResponseEntity.ok(validationResult); // 형식이 틀리면 사유 반환
        }

        // 2. 형식이 올바르면 DB 중복 여부 확인
        boolean isDuplicate = memberService.checkIdDuplicate(memberDTO.getMemberID());
        if (isDuplicate) {
            return ResponseEntity.ok(new ValidationResponseDTO(false, "이미 사용 중인 아이디입니다."));
        }

        return ResponseEntity.ok(new ValidationResponseDTO(true, "사용 가능한 아이디입니다."));
    }
    
    // 2. 닉네임 중복확인
    @PostMapping("chk-nname-dup")
    public ResponseEntity<ValidationResponseDTO> checkNnameDup(@RequestBody MemberDTO memberDTO) {
        // 1. 형식 및 규칙 검증 먼저 수행
        ValidationResponseDTO validationResult = MemberValidationUtils.validateNickname(memberDTO.getMemberNickname());
        if (!validationResult.isValid()) {
            return ResponseEntity.ok(validationResult);
        }

        // 2. DB 중복 여부 확인
        boolean isDuplicate = memberService.checkNicknameDuplicate(memberDTO.getMemberNickname());
        if (isDuplicate) {
            return ResponseEntity.ok(new ValidationResponseDTO(false, "이미 사용 중인 닉네임입니다."));
        }

        return ResponseEntity.ok(new ValidationResponseDTO(true, "사용 가능한 닉네임입니다."));
    }
    
    // 3. 이메일 중복 확인
    @PostMapping("chk-email-dup")
    public ResponseEntity<ValidationResponseDTO> checkEmailDup(@RequestBody MemberDTO memberDTO) {
        boolean isDuplicate = memberService.checkEmailDuplicate(memberDTO.getMemberEmail());
        String message = isDuplicate ? "이미 사용 중인 이메일입니다." : "사용 가능한 이메일입니다.";
        if (isDuplicate) {
        	return ResponseEntity.ok(new ValidationResponseDTO(false, message));
        }
        return ResponseEntity.ok(new ValidationResponseDTO(true, message));
    }



    // 4. 사업자 번호 중복 확인
    @PostMapping("chk-biznum-dup")
    public ResponseEntity<ValidationResponseDTO> checkBiznumDup(@RequestBody MemberDTO memberDTO) {
        // 1. 형식 및 체크섬 유효성 검증 먼저 수행
        ValidationResponseDTO validationResult = MemberValidationUtils.validateBusinessNumber(memberDTO.getBusinessNumber());
        if (!validationResult.isValid()) {
            return ResponseEntity.ok(validationResult); // 형식이 틀리면 반려 사유 반환
        }

        // 2. 형식이 올바르면 DB 중복 여부 확인
        boolean isDuplicate = memberService.checkBizNumDuplicate(memberDTO.getBusinessNumber());
        if (isDuplicate) {
            return ResponseEntity.ok(new ValidationResponseDTO(false, "이미 등록된 사업자 번호입니다."));
        }

        return ResponseEntity.ok(new ValidationResponseDTO(true, "사용 가능한 사업자 번호입니다."));
    }
}