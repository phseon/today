package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.myinfo.dao.MyInfoDAO;

public class ModifyPasswordAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.jsp";
		} */
		
		// 로그인된 경우
		request.setCharacterEncoding("utf-8");
		
		// 전송받은 데이터 저장하기
		String id = request.getParameter("id");
		String origin_pwd = request.getParameter("origin_pwd");
		String pwd = request.getParameter("pwd");
		String check_pwd = request.getParameter("check_pwd");
		
		// 로그인 dao 생성되면 하기 (아이디 체크 메서드 생성되면 하기)
		
		
		return "/WEB-INF/views/myinfo/modifyPassword.jsp";
	}

}
