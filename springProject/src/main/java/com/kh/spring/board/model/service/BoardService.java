package com.kh.spring.board.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

public interface BoardService  {
	//게시글 리스트 조회 + 페이징처리 
	
	// 아래는 게시글 총개수
	int selectListCount();
	
	
	//게시글 리스트 조회 
	ArrayList<Board>selectList(PageInfo pi);

	
	//아래는 게시글 작성 (사진포함)
	int insertBoard(Board b);
	
	//게시글 상세 조회 
	Board boardDetailView(int bno);
	
	//아래는 게시글 조회수 증가 
	int increaseCount(int BoardNo);
	
	//아래는 게시물 상세 보기 -선생님  -현재 선생님 버전으로 진행중
	Board selectBoard(int BoardNo);
	
	//게시글 삭제 
	int deleteBoard(int bno);
	
	//게시글 수정
	int updateBoard(Board b);

	//게시글 리뷰작성 
	ArrayList<Reply> detailBoardReviewSelect(int bno);

	//댓글 등록
	int insertReply(Reply r);

	//조회수 top5 메인에 게시글 조회

	ArrayList<Board> topBoard();


	




	
}
