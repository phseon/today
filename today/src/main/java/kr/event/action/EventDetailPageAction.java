package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.StringUtil;

public class EventDetailPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//글번호
		int e_num = Integer.parseInt(request.getParameter("e_num"));
		
		//관리자권한 있는지 체크, 나중에 세션에서 받아오기
		int auth = 1;
		request.setAttribute("auth", auth);
		
		EventDAO dao = EventDAO.getInstance();
		EventVO event = dao.getEvent(e_num);
		
		event.setE_title(StringUtil.useNoHtml(event.getE_title()));
		event.setE_content(StringUtil.useBrNoHtml(event.getE_content()));
		
		request.setAttribute("event", event);
		
		return "/WEB-INF/views/event/eventDetailPage.jsp";
	}

}