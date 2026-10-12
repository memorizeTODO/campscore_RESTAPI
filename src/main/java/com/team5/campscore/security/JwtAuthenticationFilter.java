package com.team5.campscore.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException; // IOException은 java.io 
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { // 모든 요청당 정확히 한 번만 실행되는 필터

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // 1. Request Header에서 토큰을 추출
        String token = resolveToken(request);

        // 2. 토큰이 존재하고 유효한지 검증
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 3. 토큰에서 사용자 이름(username)을 추출
            String username = jwtTokenProvider.getUsername(token);
            
            // 4. DB 등에서 사용자 상세 정보를 조회
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 5. 스프링 시큐리티 인증 객체 생성 (사용자 정보와 권한 목록 포함)
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            
            // 6. SecurityContext에 인증 객체를 등록하여 현재 요청을 '로그인된 상태'로 만듦
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 7. 다음 필터로 요청을 전달
        filterChain.doFilter(request, response);
    }

    // 헤더(Authorization: Bearer <토큰>)에서 순수 JWT 토큰 문자열만 추출하는 헬퍼 메서드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 문자열을 떼어내고 실제 토큰만 반환
        }
        return null;
    }
}