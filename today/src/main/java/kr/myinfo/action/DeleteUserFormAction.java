package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class DeleteUserFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	// 세션 받아 로그인여부 체크하기 
	HttpSession session = request.getSession();
	Integer user_num = (Integer)session.getAttribute("user_num");
	
	if(user_num==null) { 
		return "redirect:/member/loginForm.jsp";
	}
		

		
		return "/WEB-INF/views/myinfo/deleteUserForm.jsp";
	}

}
