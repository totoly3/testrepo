package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;
	
	//@Component
	//주로 DAO : DB (저장소) 와 관련된 작업 (영속성 작업)
	@Repository //Dao bean 으로 등록하겠다 .
	public class MemberDao {
		
	//아래는 로그인
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		
		return sqlSession.selectOne("memberMapper.loginMember",m);
	}
	
	
	//아래는 암호화 이후 회원가입 
	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember",m);
	}

	//아래는 마이페이지 에서 수정 
	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember",m);

	}
	//회원탈퇴 update 써서 n으로 바꿀꺼임 

	public int deleteMember(SqlSessionTemplate sqlSession,String userId) {
		
		return sqlSession.update("memberMapper.deleteMember",userId);
	}

	//아래는 아이디중복체크 
	public int idcheck(SqlSessionTemplate sqlSession, String idcheck) {
		
		return sqlSession.selectOne("memberMapper.idcheck",idcheck);
	}

}
