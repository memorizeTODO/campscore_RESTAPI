package com.team5.campscore.dao;

import com.team5.campscore.model.MemberSignupRequestDTO;

public interface MemberDAO {
    void insertMember(MemberSignupRequestDTO memberDTO);

	boolean checkIdDuplicate(String memberID);

	boolean checkNicknameDuplicate(String memberNickname);

	public boolean checkEmailDuplicate(String memberEmail);
	
	boolean checkBizNumDuplicate(String businessNumber);
}