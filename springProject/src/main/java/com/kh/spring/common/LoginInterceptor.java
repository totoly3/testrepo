package com.kh.spring.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;


import com.kh.spring.member.model.vo.Member;

public class LoginInterceptor implements HandlerInterceptor{
	/*
	 * Interceptor (정확히 는 HandlerInterceptor 
	 * 	-해당 Controller가 실해왿기 전 , 실행 후에 요청을 가로채 실행할 내용을 작성할수있따 
	 * 	-주로 로그이 ㄴ유무판단 ,권한 확인등에 사용되나 .
	 * 
	 * 	- Interceptor 메소드의 종류 (오버라이딩하여 사용할것) 
	 * 	preHandler(전처리) : DispatcherServlet 에서 컨트롤러를 호출하기 전에 실행되는 영역 
	 * 	postHandler(후처리) : 컨트롤러에서 요청처리후 DISPAcherServlet 으로 정보가 리턴되는 순간 실행되는 영역
	 * 
	 * 
	 * */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//true 를 리턴 하면 기존 요청했던 요청이 그대로 실행된다 . 
		//false 를 리턴하면 Controller 가 실행되지 않는다 . 
		
		//로그인 되어있는지 아닌지 확인하는 작업 
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		
//		if(loginUser != null) { //로그인 되어있는 정보가 있다면  그대로 controller 요청 실행
//			return true;
//		}else {
//			//로그인 되어있지 않는 경우 
//			session.setAttribute("alertMsg", "로그인후 가능한 서비스 입니다. ");
//			response.sendRedirect(request.getContextPath());
//			return false;
//		}
		
		
		if(!loginUser.getUserId().equals("admin")) { //로그인 되어있는 정보가 있다면  그대로 controller 요청 실행
		return true;
	}else {
		//로그인 되어있지 않는 경우 
		session.setAttribute("alertMsg", "로그인후 가능한 서비스 입니다. ");
		response.sendRedirect(request.getContextPath());
		return false;
	}
		
	}
}
