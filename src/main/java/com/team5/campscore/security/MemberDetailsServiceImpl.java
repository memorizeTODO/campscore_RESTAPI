package com.team5.campscore.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team5.campscore.model.MemberDTO;
import com.team5.campscore.service.MemberDAOImpl;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service // 스프링이 빈으로 인식할 수 있도록 @Service를 붙여줍니다.
@RequiredArgsConstructor
public class MemberDetailsServiceImpl implements UserDetailsService {

	private final MemberDAOImpl memberDAO;

    @Override
    public UserDetails loadUserByUsername(String memberID) throws UsernameNotFoundException {
        // 1. DB에서 회원 정보 조회
        MemberDTO member = memberDAO.findByMemberID(memberID);
        System.out.println(">>> 조회된 MemberDTO 객체: " + member);
        
        if (member == null) {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + memberID);
        }
        
        System.out.println("========== DB 조회 결과 확인 ==========");
        System.out.println("조회된 아이디: " + member.getMemberID());
        System.out.println("조회된 암호화 비밀번호: " + member.getMemberPasswd());
        System.out.println("========================================");

        // 2. memberType을 Spring Security 권한 형식으로 변환
        // 예: "general" -> "ROLE_GENERAL", "business" -> "ROLE_BUSINESS", "administrator" -> "ROLE_ADMINISTRATOR"
        String roleValue = member.getMemberType(); 
        if (roleValue == null || roleValue.isBlank()) {
            roleValue = "GENERAL"; // 값이 없을 경우 기본값 설정
        }
        
        // 대문자로 변환 후 ROLE_ 접두사 결합
        String authorityString = "ROLE_" + roleValue.toUpperCase();

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(authorityString)
        );

        // 3. UserDetails 객체 반환
        return new MemberDetails(member, authorities);
    }
}