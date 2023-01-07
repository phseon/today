package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class EventToReserveCheckAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer m_num = (Integer)session.getAttribute("m_num");
		if(m_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		return "/WEB-INF/views/main/main.jsp";//나중에 예약페이지로 넘겨주기
	}

}
