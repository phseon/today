package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class EventToReserveCheckAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/event/callLoginForm.do";
		}
		Integer user_auth = (Integer) session.getAttribute("user_auth");
		if (user_auth == 1) {// 0 : 탈퇴 1 : 의사 2 :일반
			request.setAttribute("accessMsg", "관리자는 예약할 수 없습니다.");
			request.setAttribute("accessUrl", "/WEB-INF/views/event/eventMainPage.jsp");
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		return "/WEB-INF/views/main/main.jsp";//나중에 예약페이지로 넘겨주기
	}

}
