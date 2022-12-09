package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	//아래 로그인 서비스 select  
	Member loginMember(Member m);
	
	//아래 회원가입 서비스  insert
	int insertMember(Member m);
	
	// 회원정보 수정 서비스  update
	int updateMember(Member m);
	
	
	// 회원 탈퇴 서비스  update / delete
	int deleteMember(String userId);

	// 아이디중복체크 서비스  select
	int idcheck(String idcheck);
	
	
	
}
