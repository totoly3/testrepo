package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

//@Component //bean으로 등록시키겠다 .
@Service  // Component 보다 좀더 구체화 시켜서 Service 로 사용할것임을 명시 
 public class MemberSeviceImple implements MemberService{
		
//	private MemberDao memberDao = new MemberDao();이건 기존방식	
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//아래는 로그인 
	@Override
	public Member loginMember(Member m) {
		Member loginUser =memberDao.loginMember(sqlSession,m);
		//SqlSessionTemplate 객체를 bean 등록 후  @Autowired 해줌으로써 
		//스프링 컨테이너가 직접 생명주기를 관리하기 때문에 따로 close 를 해줄 필요가 없다 . 
		return loginUser;
	}
	
	//암호화 처리 이 후 회원가입 
	@Override
	public int insertMember(Member m) {
		return 	memberDao.insertMember(sqlSession,m);
		
	}

	//아래는 마이페이지 에서 수정 
	@Override
	public int updateMember(Member m) {
		return 	memberDao.updateMember(sqlSession,m);
		
	}
	
	//아래는 업데이트 구문써서  n 으로 바꾸는 회원탈토ㅓㅣ
	@Override
	public int deleteMember(String userId) {

		return memberDao.deleteMember(sqlSession,userId);
	}

	//아래는 아이디 중복체크(에이젝스)
	@Override
	public int idcheck(String idcheck) {
		return memberDao.idcheck(sqlSession,idcheck);
		
	}
	

}
