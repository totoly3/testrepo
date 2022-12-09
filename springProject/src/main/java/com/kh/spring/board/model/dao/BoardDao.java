package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

@Repository
public class BoardDao {
	
	
	//게시글 갯수 조회 메소드 
	public int selectListCount(SqlSessionTemplate sqlSession) {
		int result = sqlSession.selectOne("boardMapper.selectListCount");
		return result ;
		
	}
	
	//게시글 조회 메소드 
	public ArrayList<Board> selectList(SqlSessionTemplate sqlSession, PageInfo pi) {
		int limit = pi.getBoardLimit();
		int offset =(pi.getCurrentPage()-1)* limit;	
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		ArrayList<Board>list=(ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds);
		return list;
		
	}
	
	//아래는 게시물 상세 보기 -내가 한거
	public Board boardDetailView(SqlSessionTemplate sqlSession,int bno) {
		return sqlSession.selectOne("boardMapper.boardDetailView",bno);
		
	}
	
	//아래는 게시물 등록  (사진포함)
	public int insertBoard(SqlSessionTemplate sqlSession, Board b) {
		
		return sqlSession.insert("boardMapper.insertBoard",b);
		
	}
	
	//아래는 게시물 상세 보기 -선생님  -현재 선생님 버전으로 진행중
	public Board selectBoard(SqlSessionTemplate sqlSession, int boardNo) {
		
		return sqlSession.selectOne("boardMapper.selectBoard",boardNo);
		
	}
	
	//조회수증가
	public int increaseCount(SqlSessionTemplate sqlSession,int boardNo) {
		
	return sqlSession.update("boardMapper.increaseCount",boardNo);
			
	}
	
	//아래는  업데이트 
	public int updateBoard(SqlSessionTemplate sqlSession, Board b) {
		return sqlSession.update("boardMapper.updateBoard",b);

	}
	
	//게시물 삭제
	public int deleteBoard(SqlSessionTemplate sqlSession,int bno) {
		
		return sqlSession.update("boardMapper.deleteBoard",bno);
	}
	
	//게시판 상세보기 댓글 전체조회 
	public ArrayList<Reply> detailBoardReviewSelect(SqlSessionTemplate sqlSession, int bno) {
		ArrayList<Reply> rlist = (ArrayList)sqlSession.selectList("boardMapper.detailBoardReviewSelect",bno);
		
		
		return rlist;
	}
	//아래는 댓글등록 
	public int insertReply(SqlSessionTemplate sqlSession, Reply r) {
		
		return sqlSession.insert("boardMapper.insertReply",r);
	}
	//아래는 top 5
	public ArrayList<Board> topBoard(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.topBoard");
		
	}

}
