package com.team5.campscore.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service // 스프링이 빈으로 인식할 수 있도록 @Service를 붙여줍니다.
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 지금은 DB 조회를 하지 않고, 토큰에 들어있던 username을 바탕으로 
        // 권한이 없는 빈 사용자 객체를 대충 만들어서 리턴해줍니다.
        return new User(username, "", new ArrayList<>());
    }
}