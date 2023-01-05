package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "";
		}*/
		
		// 로그인 중일 때
		request.setCharacterEncoding("utf-8");
		
		// 폼에서 입력한 데이터 변수로 저장
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		/*
		// 로그인한 아이디 변수로 저장
		String user_id = (String)session.getAttribute("user_id");
		
		// 로그인한 아이디와 입력한 아이디 비교
		
		
		*/
		
		return "/WEB-INF/views/myinfo/deleteUser.jsp";
	}

}
