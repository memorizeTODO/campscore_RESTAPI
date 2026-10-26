package com.team5.campscore.dao;

import org.apache.ibatis.annotations.Param;

import com.team5.campscore.model.MemberDTO;

public interface MemberDAO {
    void insertMember(MemberDTO memberDTO);

	boolean checkIdDuplicate(String memberID);

	boolean checkNicknameDuplicate(String memberNickname);

	public boolean checkEmailDuplicate(String memberEmail);
	
	boolean checkBizNumDuplicate(String businessNumber);
	
	MemberDTO findByMemberID(@Param("memberID") String memberID);
}