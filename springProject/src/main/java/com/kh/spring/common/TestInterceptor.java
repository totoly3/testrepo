package com.kh.spring.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring.member.model.vo.Member;

public class TestInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
	
		return HandlerInterceptor.super.preHandle(request, response, handler);
	
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");

		if(loginUser.getUserId().equals("admin")) { //로그인 되어있는 정보가 있다면  그대로 controller 요청 실행
		return true;
	}else {
		//로그인 되어있지 않는 경우 
		session.setAttribute("alertMsg", "로그인후 가능한 서비스 입니다. ");
		response.sendRedirect(request.getContextPath());
		return false;
	}
	
	}
}
