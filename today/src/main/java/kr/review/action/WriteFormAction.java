package kr.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class WriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
//		Integer m_num =
//				(Integer)session.getAttribute("m_num");
//		if(m_num==null) {//로그인이 되지 않은 경우
			return "redirect:/main/main.do";
//			return "/WEB-INF/views/main/main.jsp";
//		}
		//로그인 된 경우
//		return "/WEB-INF/views/review/writeForm.jsp";
	}

}
