package com.team5.campscore.security;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component // 스프링 빈으로 등록하여 다른 클래스에서 주입받아 사용할 수 있도록 함
public class JwtTokenProvider {

    @Value("${jwt.secret}") // application.properties에 설정한 secret 값을 주입받음
    private String secretKey;

    @Value("${jwt.expiration}") // application.properties에 설정한 만료 시간 값을 주입받음
    private long validityInMilliseconds;

    private SecretKey key;

    @PostConstruct // 빈이 생성된 후 초기화 작업을 수행 (시크릿 키를 HMAC-SHA 알고리즘용 Key 객체로 변환)
    public void init() {
        byte[] bytes = Decoders.BASE64.decode(Base64.getEncoder().encodeToString(secretKey.getBytes()));
        this.key = Keys.hmacShaKeyFor(bytes);
    }

    // 1. 토큰 생성 메서드: 사용자의 이름을 받아 JWT 토큰을 발급
    public String createToken(String username) {
        // 토큰의 Payload에 들어갈 정보(Claims) 설정 (여기서는 subject에 username 저장)
        Claims claims = Jwts.claims().subject(username).build();
        
        Date now = new Date();
        // 현재 시간에 유효 시간을 더해 만료 시간 설정
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .claims(claims)            // 정보 담기
                .issuedAt(now)             // 토큰 발행 시간
                .expiration(validity)      // 토큰 만료 시간
                .signWith(key)             // 시크릿 키로 서명 (변조 방지)
                .compact();                // 최종적으로 압축 및 직렬화하여 토큰 문자열 반환
    }

    // 2. 토큰에서 사용자 이름(Username) 추출 메서드
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)           // 서명 검증을 위한 키 설정
                .build()
                .parseSignedClaims(token)  // 토큰 파싱
                .getPayload()
                .getSubject();             // 저장해 둔 subject(username) 반환
    }

    // 3. 토큰 유효성 검증 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true; // 예외가 발생하지 않으면 유효한 토큰
        } catch (JwtException | IllegalArgumentException e) {
            return false; // 만료되었거나 변조된, 혹은 잘못된 형식의 토큰인 경우
        }
    }
    
    // 메인 액세스 토큰(또는 인증된 사용자 정보)을 기반으로 파생된 단기 verification토큰 발급 (유효기간 5분)
    public String generateDerivedVerificationToken(String username) {
        Date now = new Date();
        long editTokenValidityInMilliseconds = 5 * 60 * 1000L; // 5분
        Date validity = new Date(now.getTime() + editTokenValidityInMilliseconds);

        return Jwts.builder()
                .subject(username) // setSubject -> subject 로 변경
                .claim("type", "EDIT_TOKEN")
                .issuedAt(now)     // setIssuedAt -> issuedAt 로 변경
                .expiration(validity) // setExpiration -> expiration 로 변경
                .signWith(key)     // 알고리즘 매개변수가 빠지고 key 객체만 전달
                .compact();
    }

    // 2. 파생된 단기 수정 토큰 검증 및 파싱 
    public boolean validateVerificationToken(String token, String username) {
        try {
            // parserBuilder() 대신 parser() 사용 후 키 지정
            Claims claims = Jwts.parser()
                    .verifyWith(key) // setSigningKey -> verifyWith 로 변경
                    .build()
                    .parseSignedClaims(token) // parseClaimsJws -> parseSignedClaims 로 변경
                    .getPayload(); // getBody -> getPayload 로 변경

            String tokenType = claims.get("type", String.class);
            String tokenUsername = claims.getSubject();

            return "EDIT_TOKEN".equals(tokenType) && tokenUsername.equals(username);
        } catch (Exception e) {
            return false;
        }
    }
}