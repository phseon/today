package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.StringUtil;

public class EventUpdateFormPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//권한체크 나중에 세션에서 받아오기
		int auth = 1;
		request.setAttribute("auth", auth);
		
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		
		EventDAO dao = EventDAO.getInstance();
		EventVO event = dao.getEvent(e_num);
		
		event.setE_title(StringUtil.parseQuot(event.getE_title()));
		
		request.setAttribute("event", event);
		
		return "/WEB-INF/views/event/eventUpdateFormPage.jsp";
	}

}
