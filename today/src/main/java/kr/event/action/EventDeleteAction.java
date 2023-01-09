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
		/*
		HttpSession session = request.getSession();
		Integer m_num = (Integer)session.getAttribute("m_num");
		if(m_num == null) { //로그인이 안된경우
			return "redirect:/member/loginForm.do";
		}
		*/
		int auth = 1;
		request.setAttribute("auth", auth);
		
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		EventDAO dao = EventDAO.getInstance();
		EventVO db_event = dao.getEvent(e_num);
		if(auth != 1) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//이벤트 글삭제
		dao.deleteEvent(e_num);
		
		//파일삭제
		FileUtil.removeFile(request, db_event.getE_imgsrc());
		FileUtil.removeFile(request, db_event.getE_thumb());
		
		return "redirect:/event/eventPage.do";
	}

}
