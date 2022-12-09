package com.kh.spring.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
	public class PageInfo {
		private int listCount; //총 게시글 갯구
		private int currentPage; //현재페이지
		private int pageLimit; //하단에 보여질 페이징바 갯수
		private int boardLimit; //한페이지에 보여질 게시글 갯수
		
		private int maxPage; //가장 마지막 페이지수 
		private int startPage;  // 페이징바 시작수 
		private int endPage;	//페이징바 끝수
	
		
}
