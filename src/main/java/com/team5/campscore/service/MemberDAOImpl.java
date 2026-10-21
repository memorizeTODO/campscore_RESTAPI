package com.team5.campscore.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository; // 또는 @Service

import com.team5.campscore.dao.MemberDAO;
import com.team5.campscore.model.MemberSignupRequestDTO;

@Repository // 또는 기존 스타일처럼 @Service를 쓰셔도 스프링 빈으로 등록됩니다.
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSessionTemplate sqlSession; // 혹은 주입받고자 하는 MyBatis 객체나 다른 매퍼

    @Override
    public void insertMember(MemberSignupRequestDTO memberDTO) {
        sqlSession.insert("com.team5.campscore.dao.MemberDAO.insertMember", memberDTO);
    }
    
 // 아이디 중복 체크
    @Override
    public boolean checkIdDuplicate(String memberID) {
        int count = sqlSession.selectOne("com.team5.campscore.dao.MemberDAO.checkIdDuplicate", memberID);
        return count > 0; // 0보다 크면 중복(true), 아니면 사용 가능(false)
    }

    // 닉네임 중복 체크
    @Override
    public boolean checkNicknameDuplicate(String memberNickname) {
        int count = sqlSession.selectOne("com.team5.campscore.dao.MemberDAO.checkNicknameDuplicate", memberNickname);
        return count > 0;
    }

    // 사업자 번호 중복 체크
    @Override
    public boolean checkBizNumDuplicate(String businessNumber) {
        int count = sqlSession.selectOne("com.team5.campscore.dao.MemberDAO.checkBizNumDuplicate", businessNumber);
        return count > 0;
    }
}