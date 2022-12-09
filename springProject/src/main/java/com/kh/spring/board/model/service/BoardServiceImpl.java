package com.kh.spring.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;


@Service
public class BoardServiceImpl implements BoardService {

	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//아래는 게시글 갯수 확인 
	@Override
	public int selectListCount() {
		int listCount =boardDao.selectListCount(sqlSession);
		
		return listCount;
	}
	

	//게시글 리스트 조회 
	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		ArrayList<Board> list	=boardDao.selectList(sqlSession,pi);
		return list;
	}
	
	//게시글 등록 (사진포함)
	@Override
	public int insertBoard(Board b) {
		
		return boardDao.insertBoard(sqlSession,b);
	}
	
	@Override
	public int increaseCount(int BoardNo) {
		return boardDao.increaseCount(sqlSession,BoardNo);
		
	}

	//아래는 게시물 상세 보기 -선생님  -현재 선생님 버전으로 진행중
	@Override
	public Board selectBoard(int BoardNo) {
		return boardDao.selectBoard(sqlSession,BoardNo);
		
	
	}
	//아래는 삭제 
	@Override
	public int deleteBoard(int bno) {
		return boardDao.deleteBoard(sqlSession,bno);
		
	}
	
	//아래는 게시판 업데이트 
	@Override
	public int updateBoard(Board b) {
	int result=boardDao.updateBoard(sqlSession,b);
		System.out.println("업데이트 서비스인데 여긴 1일까? : "+result);
		return result;
	}

	//아래는 게시판 상세 보기 
	@Override
	public Board boardDetailView(int bno) {
		return boardDao.boardDetailView(sqlSession,bno);
		
	}

	//아래는  detailview 리뷰 전체조회 
	@Override
	public ArrayList<Reply> detailBoardReviewSelect(int bno) {
		
		ArrayList<Reply> rlist = boardDao.detailBoardReviewSelect(sqlSession,bno);
		
		return rlist;
	}

	//아래는 댓글등록 
	@Override
	public int insertReply(Reply r) {
		
		return boardDao.insertReply(sqlSession,r);
	}

	// top list5
	@Override
	public ArrayList<Board> topBoard() {
		
		return boardDao.topBoard(sqlSession);
	}

}
