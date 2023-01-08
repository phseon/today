package kr.myinfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class ModifyPasswordFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	// 세션이 존재하는지 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");
		
		if(user_num==null) {
			return "redirect:/member/login.do";
		}  
		
		
		
		return "/WEB-INF/views/myinfo/modifyPasswordForm.jsp";
	}

}
