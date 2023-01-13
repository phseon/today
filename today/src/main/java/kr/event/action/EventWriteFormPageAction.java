package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class EventWriteFormPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//eventMainPage.jsp에서 글쓰기 버튼 누를 시 호출
		
		 HttpSession session = request.getSession(); 
		 Integer user_num = (Integer)session.getAttribute("user_num"); 
		 if(user_num == null) { 
		 return "redirect:/event/callLoginForm.do";
		 } 
		 Integer user_auth = (Integer)session.getAttribute("user_auth");
		 
		 String pageNum = request.getParameter("pageNum");
			if(pageNum == null) pageNum = "1";

		if(user_auth != 1) {// 0 : 탈퇴 1 : 의사 2 :일반
			request.setAttribute("accessMsg", "이벤트 글작성 권한이 없습니다.");
			request.setAttribute("accessUrl", "/WEB-INF/views/event/eventMainPage.jsp");
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//권한 체크 후 form 페이지로 이동
		return "/WEB-INF/views/event/eventWriteFormPage.jsp";
	}

}
