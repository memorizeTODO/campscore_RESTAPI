package com.team5.campscore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.MemberSignupRequestDTO;
import com.team5.campscore.service.MemberDAOImpl;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberDAOImpl memberService;

    @PostMapping("signup")
    public ResponseEntity<String> signupMember(@RequestBody MemberSignupRequestDTO memberDTO) {
        try {
            // 이메일과 아이디 동기화 처리 (필요시)
            if (memberDTO.getMemberID() != null && memberDTO.getMemberEmail() == null) {
                memberDTO.setMemberEmail(memberDTO.getMemberID());
            }

            memberService.insertMember(memberDTO);
            return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("회원가입 실패: " + e.getMessage());
        }
    }
    
    // 1. 아이디(이메일) 중복 확인
    @PostMapping("chk-id-dup")
    public ResponseEntity<Boolean> checkIdDup(@RequestBody MemberSignupRequestDTO memberDTO) {
        boolean isDuplicate = memberService.checkIdDuplicate(memberDTO.getMemberID());
        return ResponseEntity.ok(isDuplicate);
    }

    // 2. 닉네임 중복 확인
    @PostMapping("chk-nname-dup")
    public ResponseEntity<Boolean> checkNnameDup(@RequestBody MemberSignupRequestDTO memberDTO) {
        boolean isDuplicate = memberService.checkNicknameDuplicate(memberDTO.getMemberNickname());
        return ResponseEntity.ok(isDuplicate);
    }

    // 3. 사업자 번호 중복 확인
    @PostMapping("chk-biznum-dup")
    public ResponseEntity<Boolean> checkBiznumDup(@RequestBody MemberSignupRequestDTO memberDTO) {
        boolean isDuplicate = memberService.checkBizNumDuplicate(memberDTO.getBusinessNumber());
        return ResponseEntity.ok(isDuplicate);
    }
}