package kr.information.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class WriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth= (Integer)session.getAttribute("user_auth");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}else if(user_auth==2) {
			return "/WEB-INF/views/information/autherror.jsp";
		}
		//로그인 된 경우
		return "/WEB-INF/views/information/writeForm.jsp";
	}

}
