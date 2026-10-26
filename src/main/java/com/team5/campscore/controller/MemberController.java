package com.team5.campscore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.campscore.model.MemberDTO;
import com.team5.campscore.service.MemberDAOImpl;

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
    
    // 1. 아이디 중복 확인
    @PostMapping("chk-id-dup")
    public ResponseEntity<Boolean> checkIdDup(@RequestBody MemberDTO memberDTO) {
        boolean isDuplicate = memberService.checkIdDuplicate(memberDTO.getMemberID());
        return ResponseEntity.ok(isDuplicate);
    }
    
    @PostMapping("chk-email-dup")
    public ResponseEntity<Boolean> checkEmailDup(@RequestBody MemberDTO memberDTO) {
        boolean isDuplicate = memberService.checkEmailDuplicate(memberDTO.getMemberEmail());
        return ResponseEntity.ok(isDuplicate);
    }

    // 2. 닉네임 중복 확인
    @PostMapping("chk-nname-dup")
    public ResponseEntity<Boolean> checkNnameDup(@RequestBody MemberDTO memberDTO) {
        boolean isDuplicate = memberService.checkNicknameDuplicate(memberDTO.getMemberNickname());
        return ResponseEntity.ok(isDuplicate);
    }

    // 3. 사업자 번호 중복 확인
    @PostMapping("chk-biznum-dup")
    public ResponseEntity<Boolean> checkBiznumDup(@RequestBody MemberDTO memberDTO) {
        boolean isDuplicate = memberService.checkBizNumDuplicate(memberDTO.getBusinessNumber());
        return ResponseEntity.ok(isDuplicate);
    }
}