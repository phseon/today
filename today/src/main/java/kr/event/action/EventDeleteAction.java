package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class EventDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 HttpSession session = request.getSession(); 
		 Integer user_num = (Integer)session.getAttribute("user_num"); 
		 if(user_num == null) { 
		 return "redirect:/event/callLoginForm.do";
		 } 
		 Integer user_auth = (Integer)session.getAttribute("user_auth");

		if(user_auth != 1) {// 0 : 탈퇴 1 : 의사 2 :일반
			request.setAttribute("accessMsg", "이벤트 글삭제 권한이 없습니다.");
			request.setAttribute("accessUrl", "/WEB-INF/views/event/eventMainPage.jsp");
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		EventDAO dao = EventDAO.getInstance();
		EventVO db_event = dao.getEvent(e_num);
		
		//이벤트 글삭제
		dao.deleteEvent(e_num);
		
		//파일삭제
		FileUtil.removeFile(request, db_event.getE_imgsrc());
		FileUtil.removeFile(request, db_event.getE_thumb());
		
		return "redirect:/event/eventPage.do";
	}

}
