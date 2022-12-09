package com.kh.spring.common.template;

import com.kh.spring.common.model.vo.PageInfo;

public class Pagination {
public static PageInfo getPageinfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		
	int maxPage = (int)(Math.ceil(((double)listCount / boardLimit)));//가장 마지막 페이지수 
	
	int startPage = (currentPage-1)/pageLimit * pageLimit + 1; // 페이징바 시작수 
			
	int endPage = startPage + pageLimit - 1; //페이징바 끝수
	
	//startPage가 11일때 endPage는 20으로 된다 (만약 maxPage가 13이라면?)
	//endPage를 maxPage로 변경
	if(endPage>maxPage) {
		endPage = maxPage;
	}
//	PageInfo(listCount,boardLimit,currentPage,pageLimit,maxPage,startPage,endPage);
//	return pi;
	//아래는 두줄짜리 하나로 합친거 ! 
	return new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
}
}
