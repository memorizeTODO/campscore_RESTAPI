package com.team5.campscore.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.team5.campscore.model.MemberDTO;
import java.util.Collection;

public class MemberDetails implements UserDetails {
    
    private final MemberDTO member;
    private final Collection<? extends GrantedAuthority> authorities;

    public MemberDetails(MemberDTO member, Collection<? extends GrantedAuthority> authorities) {
        this.member = member;
        this.authorities = authorities;
    }

    // 닉네임을 꺼내 쓸 수 있는 커스텀 메서드
    public String getMemberNickname() {
        return member.getMemberNickname();
    }

    // 원본 MemberDTO가 필요할 때 꺼내 쓰는 메서드
    public MemberDTO getMember() {
        return member;
    }

    @Override
    public String getUsername() {
        return member.getMemberID(); // Spring Security가 인식하는 고유 아이디
    }

    @Override
    public String getPassword() {
        return member.getMemberPasswd(); // Spring Security가 검증할 비밀번호
    }
    
 // [추가] 일반/사업자 회원 유형을 반환하는 메서드 (예: "general", "business" 등)
    public String getMemberType() {
        return member.getMemberType();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // 권한 목록
    }

    // 계정 상태 관련 설정 (필요에 따라 DB 컬럼과 연동 가능, 우선은 모두 true 처리)
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}