package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class EventWriteFormPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		/*
		 * HttpSession session = request.getSession(); 
		 * Integer m_num = (Integer)session.getAttribute("m_num"); 
		 * if(m_num == null) { 
		 * return "redirect:/member/loginForm.do";
		 * } 
		 * Integer auth = (Integer)session.getAttribute("auth");
		 */
		//일단 받아오기 전까지 데이터 강제 세팅 나중에 해제
		int auth = 1;
		if(auth != 1) {// 0 : 탈퇴 1 : 의사 2 :일반
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		
		return "/WEB-INF/views/event/eventWriteFormPage.jsp";
	}

}
